package webb.domain;

import webb.client.model.puzzle.Cell;

public class domainTest {
    public static void main(String[] args){
        Cell cell = new Cell(0, 0);
        cell.changeType(true);
        cell.changeType(true);
        cell.changeType(true);
        cell.changeType(true);
        cell.changeType(false);
        System.out.print("Hello");
    }
}
