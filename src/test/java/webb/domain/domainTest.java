package webb.domain;

import webb.shared.dtos.old.PuzzleDTO_OLD;

import java.io.File;
import java.io.IOException;
import webb.client.ui.screens.puzzlescreen.PuzzleComponent;

public class domainTest {
    public static void main(String[] args) throws IOException {
        PuzzleDTO_OLD PUZZLE_1_1_1 = PuzzleDTO_OLD.fromJSON(new File("puzzles/puzzle-1-1-1.json"));

        //This is only for testing! Not a good idea!
        PuzzleComponent p = new PuzzleComponent();
        p.setPuzzle(PUZZLE_1_1_1);

        p.printBoard();
        System.out.println();

        p.getCell(0,0).onClick(false);
        p.getCell(4, 0).onClick(false);
        p.getCell(5, 0).onClick(false);
        p.getCell(7,0).onClick(false);
        p.getCell(0, 5).onClick(false);
        p.getCell(4,0).onClick(false);

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
