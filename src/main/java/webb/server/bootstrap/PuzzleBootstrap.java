package webb.server.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;
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

        //sort puzzles. This way ID 1 is 1-1-1 and the same every time
        Arrays.sort(puzzleFiles, Comparator.comparing(File::getName, new PuzzleNameComparator()));

        for(File f : puzzleFiles) {
            System.out.println(f.getName());
        }

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
                    logger.info("Imported puzzle: " + puzzleFile.getName() + " as " + saveable_puzzle.getId());
                    repo.save(saveable_puzzle);
                } catch (Exception e) {
                    logger.error("Unable to load file: " + puzzleFile.getPath(), e);
                }
            }
        }
        logger.info("Successfully loaded " + generatedId + " puzzle files into the database.");
    }

    /*
    Sort puzzles in alphabetical order, but also numerically. This way, 1-1-1 comes before 1-1-2, and 2-1-3 comes before 1-1-1 etc.
     */
    private static class PuzzleNameComparator implements Comparator<String> {

        private static final Pattern REGEX = Pattern.compile("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        @Override
        public int compare(String o1, String o2) {

            final String[] splt1 = REGEX.split(o1);
            final String[] splt2 = REGEX.split(o2);

            for (int i = 0; i < Math.min(splt1.length, splt2.length); i++) {
                final char splt1first = splt1[i].charAt(0);
                final char splt2first = splt2[i].charAt(0);
                int compare = 0;

                //If both names start with a digit, then numerically compare them
                if (splt1first >= '0' && splt1first <= '9' && splt2first >= '0' && splt2first <= '9') {
                    BigInteger number1 = new BigInteger(splt1[i]);
                    BigInteger number2 = new BigInteger(splt2[i]);
                    compare = number1.compareTo(number2);
                }

                //sort lexicographically if we didnt sort numerically yet
                if (compare == 0) {
                    compare = splt1[i].compareTo(splt2[i]);
                }

                // Once we find a difference between the two strings, we can return
                if (compare != 0) {
                    return compare;
                }
            }

            // We should not reach this unless one of the strings is longer than the other.
            return splt1.length - splt2.length;
        }
    }
}
