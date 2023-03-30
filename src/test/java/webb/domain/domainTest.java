package webb.domain;

import webb.client.model.puzzle.CellDTO;
import webb.client.model.puzzle.Puzzle;
import webb.client.model.puzzle.PuzzleDTO;

import java.io.File;
import java.io.IOException;

public class domainTest {
    public static void main(String[] args) throws IOException {
        int[] vals = {0, 0};
        CellDTO cell = new CellDTO(vals);
        cell.changeType(true);
        cell.changeType(true);
        cell.changeType(true);
        cell.changeType(true);
        cell.changeType(false);

        PuzzleDTO PUZZLE_1_1_1 = PuzzleDTO.fromJSON(new File("puzzles/puzzle-1-1-1.json"));
        Puzzle p = new Puzzle(PUZZLE_1_1_1);

        p.getCell(0,0).changeType(true);
        p.getCell(0, 4).changeType(true);
        p.getCell(0,7).changeType(true);
        p.getCell(5, 0).changeType(true);

        p.checkBoard(true);

        p.printBoard();
        System.out.print("Hello");
    }
}
