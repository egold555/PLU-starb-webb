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
        Cell cell = new Cell(0, 0);
        cell.onClick(false);
        cell.onClick(false);
        cell.onClick(false);
        cell.onClick(false);
        cell.onClick(true);

        PuzzleDTO PUZZLE_1_1_1 = PuzzleDTO.fromJSON(new File("puzzles/puzzle-1-1-1.json"));

        //This is only for testing! Not a good idea!
        PuzzleComponent p = new PuzzleComponent();
        p.setPuzzle(PUZZLE_1_1_1);

        p.getCell(0,0).onClick(false);
        p.getCell(0, 4).onClick(false);
        p.getCell(0, 5).onClick(false);
        p.getCell(0,7).onClick(false);
        p.getCell(5, 0).onClick(false);
        p.getCell(0,4).onClick(false);
        p.changeType(p.getCell(1,1), CellType.PLAYER_MARKER, true);
        

        p.checkBoard(true);
        p.printBoard();
        System.out.println();

        p.changeType(p.getCell(0,5), CellType.EMPTY, true);
        p.changeType(p.getCell(0,7), CellType.EMPTY, true);

        p.printBoard();
        System.out.println();

        System.out.print("Hello");
    }
}
