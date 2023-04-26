package webb.server.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import webb.server.repository.PuzzleRepository;
import webb.shared.dtos.puzzle.PuzzleLevelDTO;

import java.io.File;

@Component
public class PuzzleBootstrap implements ApplicationListener<ApplicationReadyEvent> {
    private final PuzzleRepository repo;
    private final Logger logger = LoggerFactory.getLogger(PuzzleBootstrap.class);
    private final String PUZZLES_FP;

    @Autowired
    public PuzzleBootstrap(PuzzleRepository repo, @Value("${starbo.puzzles.path}") String puzzlesPath) {
        this.repo = repo;
        this.PUZZLES_FP = puzzlesPath;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // do nothing, if already populated
        if( repo.count() != 0 ) return;

        File puzzleDirectory = new File(PUZZLES_FP);
        File[] puzzleFiles = puzzleDirectory.listFiles();

        // nothing to load
        if(puzzleFiles == null) return;

        logger.info("No puzzles in the database. Will now load from " + PUZZLES_FP);

        int generatedId = 0;
        for(File puzzleFile : puzzleFiles) {
            if(puzzleFile.isFile() && puzzleFile.getName().endsWith(".json")) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    // these puzzles, don't have the id or getSolvedByNumberPlayers key
                    PuzzleLevelDTO imported_puzzle = mapper.readValue(puzzleFile, PuzzleLevelDTO.class);
                    // in this case, a puzzle did have an id key (jackson defaults it to 0)
                    if(imported_puzzle.getId() > 0) continue;

                    if(imported_puzzle.getSolution() == null || imported_puzzle.getRegions() == null) {
                        logger.warn("No regions or solutions from puzzle file " + puzzleFile.getPath());
                        continue;
                    }

                    // and because this is final, we need to make a new PuzzleLevelDTO with the correct ids
                    PuzzleLevelDTO saveable_puzzle = new PuzzleLevelDTO(++generatedId,
                                                                        imported_puzzle.getRegions(),
                                                                        imported_puzzle.getSolution(),
                                                                        imported_puzzle.getGridSize(),
                                                                        imported_puzzle.getNumStars(),
                                                                        imported_puzzle.getSolvedByNumPlayers());
                    repo.save(saveable_puzzle);
                } catch (Exception e) {
                    logger.error("Unable to load file: " + puzzleFile.getPath(), e);
                }
            }
        }
        logger.info("Successfully loaded " + generatedId + " puzzle files into the database.");
    }
}
