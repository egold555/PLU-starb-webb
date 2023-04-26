package webb.client.logic.puzzle;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import webb.client.ui.screens.puzzlescreen.CellComponent;
import webb.shared.dtos.puzzle.CellDTO;
import webb.shared.dtos.puzzle.created.CreatePuzzleLevelDTO;

@ToString
public class PuzzleLogic {

    /**
     * @ return The size of the grid
     */
    @Getter
    private int gridSize = 10;

    /**
     * @return The number of stars in the puzzle
     */
    @Getter
    private int numStars = 0;

    //COL, ROW
    private CellComponent[][] board;

    private List<List<CellComponent>> regions;

    public PuzzleLogic() {

    }

    /**
     * Sets the size of the grid, and resets the cells
     * @param size Size of the grid
     */
    public void setGridSize(int size) {
        gridSize = size;
        board = null;
        board = new CellComponent[gridSize][gridSize];
        for(int row = 0; row < gridSize; row++ ) {
            for(int col = 0; col < gridSize; col++ ) {
                board[col][row] = new CellComponent( col, row );
            }
        }
    }

    /**
     * Set the puzzle to be displayed
     * @param puzzle Puzzle to be displayed
     */
    public void setPuzzle(CreatePuzzleLevelDTO puzzle) {

        //Set the puzzle size & reset the cells
        this.setGridSize(puzzle.getGridSize());

        this.numStars = puzzle.getNumStars();

        regions = new ArrayList<>();

        //Set the cell groups
        //Loop through every region, and set the group of every cell in that region to the region index
        for(int i = 0; i < puzzle.getRegions().size(); i++ ) {
            List<CellDTO> listOfCells = puzzle.getRegions().get(i);
            List<CellComponent> tmp = new ArrayList<>();
            for(CellDTO cellDTO : listOfCells) {
                CellComponent cell = getCell(cellDTO.getCol(), cellDTO.getRow());
                cell.setGroup(i);
                tmp.add(cell);
            }
            regions.add(tmp);
        }

        //Set the solution boolean for every cell that is in the solution
        for(CellDTO cellDTO : puzzle.getSolution()) {
            CellComponent cell = getCell(cellDTO.getCol(), cellDTO.getRow());
            cell.setSolutionStar();

            //TODO: Testing - Show the solution
            //cell.setType(CellType.STAR);
        }

        //Set cell walls
        /*
        Go through every row and column.
        If cell isn't valid (out of bounds) then it's a wall on that side.
        If the cell next or below is in a different group then it's a wall.
        else it's not a wall.
         */
        for(int row = 0; row < gridSize; row++ ) {
            for(int col = 0; col < gridSize; col++ ) {
                CellComponent cell = getCell(col, row);


                //North
                if(row == 0 || getCell(col, row - 1).getGroup() != cell.getGroup()) {
                    cell.setWall(CellLogic.WALL_NORTH);
                }

                //East
                if(col == gridSize - 1 || getCell(col + 1, row).getGroup() != cell.getGroup()) {
                    cell.setWall(CellLogic.WALL_EAST);
                }

                //South
                if(row == gridSize - 1 || getCell(col, row + 1).getGroup() != cell.getGroup()) {
                    cell.setWall(CellLogic.WALL_SOUTH);
                }

                //West
                if(col == 0 || getCell(col - 1, row).getGroup() != cell.getGroup()) {
                    cell.setWall(CellLogic.WALL_WEST);
                }

            }
        }
    }

    /**
     * Mirrors changeType from CellDTO while also clearing adjacently marked cells if c is cleared.
     * @param c
     * @param rightClick
     * @param visible
     */
    public void onClick(CellComponent c, boolean rightClick, boolean visible){
        c.onClick(rightClick);
        if(c.getType() == CellType.EMPTY || c.getType() == CellType.PLAYER_MARKER){
            clearAdjacent(c);
        }
        checkBoard(visible);
    }

    /**
     * Primary method for checking and setting if stars are in valid locations
     * @param visible
     */
    public void checkBoard(boolean visible){
        CellComponent current;
        boolean rowOut;
        boolean colOut;

        for(int i=0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                current = getCell(j,i);
                if(current.getType() == CellType.STAR || current.getType() == CellType.INVALID){
                    rowOut = checkRow(visible, current);
                    colOut = checkCol(visible, current);
                    if(!rowOut || !colOut){
                        current.setType(CellType.INVALID);
                    }
                    else{
                        current.setType(CellType.STAR);
                    }
                    markAdjacent(current);
                }
            }
        }
        checkRegions();
    }


    private boolean checkRow(boolean visible, CellComponent current){
        int rowStars = 0;
        for(int i = 0; i < gridSize; i++){
            if(getCell(i, current.getRow()).getType() == CellType.STAR || getCell(i, current.getRow()).getType() == CellType.INVALID){
                rowStars++;
            }
        }
        if(visible && rowStars>=numStars){
            for(int i = 0; i < gridSize; i++){
                if(getCell(i, current.getRow()).getType() == CellType.EMPTY || getCell(i, current.getRow()).getType() == CellType.AMARKER){
                    getCell(i, current.getRow()).setType(CellType.VMARKER);
                }
            }
        }
        if(visible && rowStars<numStars){
            for(int i = 0; i < gridSize; i++){
                if(getCell(i, current.getRow()).getType() == CellType.VMARKER){
                    getCell(i, current.getRow()).setType(CellType.EMPTY);
                }
            }
        }
        return rowStars <= numStars;
    }

    private boolean checkCol(boolean visible, CellComponent current){
        int colStars = 0;
        for(int i=0; i < gridSize; i++){
            if(getCell(current.getCol(), i).getType() == CellType.STAR || getCell(current.getCol(), i).getType() == CellType.INVALID){
                colStars++;
            }
        }
        if(visible && colStars>=numStars){
            for(int i=0; i < gridSize; i++){
                if(getCell(current.getCol(), i).getType() == CellType.EMPTY || getCell(current.getCol(), i).getType() == CellType.AMARKER){
                    getCell(current.getCol(), i).setType(CellType.VMARKER);
                }
            }
        }
        if(visible && colStars<numStars){
            for(int i=0; i < gridSize; i++){
                if(getCell(current.getCol(), i).getType() == CellType.VMARKER){
                    getCell(current.getCol(), i).setType(CellType.EMPTY);
                }
            }
        }
        return colStars <= numStars;
    }

    private void checkRegions(){
        for(List<CellComponent> region : regions){
            int regionStars = 0;
            for(CellComponent cell : region){
                if(cell.getType() == CellType.STAR || cell.getType() == CellType.INVALID){
                    regionStars++;
                }
            }
            if(regionStars > numStars){
                for(CellComponent cell : region){
                    if(cell.getType() == CellType.STAR){
                        cell.setType(CellType.INVALID);
                    }
                }
            }
        }
    }

    /**
     * If checkBoard is visible, marks adjacent cells around a star cell as AMARKER, which can be
     * cleared by clearAdjacent
     * @param current
     */
    private void markAdjacent(CellComponent current){
        int currentRow = current.getRow();
        int currentCol = current.getCol();
        CellComponent temp;

        for (int i = currentRow - 1; i < currentRow + 2; i++) {
            for (int j = currentCol - 1; j < currentCol + 2; j++) {
                if (i >= 0 && i < gridSize && j >= 0 && j < gridSize && !(i==currentRow && j==currentCol)) {
                    temp = getCell(j, i);
                    if (temp.getType() == CellType.EMPTY) {
                        temp.setType(CellType.AMARKER);
                    }
                    if(temp.getType() == CellType.STAR || temp.getType() == CellType.INVALID){
                        current.setType(CellType.INVALID);
                    }
                }
            }
        }
    }

    private void clearAdjacent(CellComponent current) {
        int currentRow = current.getRow();
        int currentCol = current.getCol();
        CellComponent temp;

        for (int i = currentRow - 1; i < currentRow + 2; i++) {
            for (int j = currentCol - 1; j < currentCol + 2; j++) {
                if (i >= 0 && i < gridSize && j >= 0 && j < gridSize) {
                    temp = getCell(j, i);
                    if (temp.getType() == CellType.AMARKER) {
                        temp.setType(CellType.EMPTY);
                    }
                }
            }
        }
    }

    public void printBoard(){
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                System.out.printf("%1s", getCell(j,i).getType().getLetter());
            }
            System.out.println();
        }
    }

    /**
     * Get a cell at a specific position
     * @param col Column of the cell
     * @param row Row of the cell
     * @return The cell at the specified position
     * @throws IllegalArgumentException if the position is invalid
     */
    public CellComponent getCell(int col, int row) {
        if(!isCellValid(col, row)) {
            throw new IllegalArgumentException("Invalid cell position!");
        }
        return board[col][row];
    }

    /**
     * Check if a cell position is valid
     * @param col Column of the cell
     * @param row Row of the cell
     * @return True if the position is valid, false otherwise
     */
    private boolean isCellValid(int col, int row) {
        return col >= 0 && col < gridSize && row >= 0 && row < gridSize;
    }
}
