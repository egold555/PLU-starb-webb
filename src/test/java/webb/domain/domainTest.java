package webb.domain;

import webb.client.model.puzzle.CellDTO;
import webb.client.model.puzzle.PuzzleDTO;

import java.io.File;
import java.io.IOException;
import webb.client.ui.screens.puzzlescreen.Cell;
import webb.client.ui.screens.puzzlescreen.Cell.CellType;
import webb.client.ui.screens.puzzlescreen.PuzzleComponent;
import webb.client.ui.screens.puzzlescreen.PuzzleScreen;

public class domainTest {
    public static void main(String[] args) throws IOException {
        PuzzleDTO PUZZLE_1_1_1 = PuzzleDTO.fromJSON(new File("puzzles/puzzle-1-1-1.json"));

        //This is only for testing! Not a good idea!
        PuzzleComponent p = new PuzzleComponent();
        p.setPuzzle(PUZZLE_1_1_1);

        p.printBoard();
        System.out.println();

        p.getCell(0,0).changeType(false);
        p.getCell(4, 0).changeType(false);
        p.getCell(5, 0).changeType(false);
        p.getCell(7,0).changeType(false);
        p.getCell(0, 5).changeType(false);
        p.getCell(4,0).changeType(false);

        p.printBoard();
        System.out.println();

        p.onClick(p.getCell(1,1), false, true);
        

        p.checkBoard(true);
        p.printBoard();
        System.out.println();

        p.onClick(p.getCell(5,0), true, true);
        p.onClick(p.getCell(7,0), true, true);

        p.printBoard();
        System.out.println();

        System.out.print("Hello");
    }
}
