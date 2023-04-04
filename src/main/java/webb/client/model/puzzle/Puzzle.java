package webb.client.model.puzzle;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import webb.client.ui.screens.puzzlescreen.Cell.CellType;

/**
 * Represents a puzzle from the JSON file.
 * Modified from: https://cs.plu.edu/courses/protected/cs390/2023s/project/iteration2.html
 */
public class Puzzle {

    private List<List<CellDTO>> regions;
    private List<CellDTO> solution;
    private int gridSize;
    private int numStars;
    private CellDTO[][] board;

    public Puzzle(PuzzleDTO p){
        this.regions = p.getRegions();
        this.solution = p.getSolution();
        this.gridSize = p.getGridSize();
        this.numStars = p.getNumStars();
        board = new CellDTO[gridSize][gridSize];
        loadBoard();
    }

    /**
     * @return the regions of the puzzle as a list of Cells
     */
    public List<List<CellDTO>> getRegions() {return regions;}

    /**
     * @return the solution to the puzzle as a list of Cells
     */
    public List<CellDTO> getSolution() {return solution;}

    /**
     * @return the size of the grid
     */
    public int getGridSize() {return gridSize;}

    /**
     * @return the number of stars per region
     */
    public int getNumStars() {return numStars;}

    /**
     * @return the total number of stars in the puzzle
     */
    public int getTotalStars() {return numStars * regions.size();}


    public void loadBoard(){
        for(List<CellDTO> r : regions){
            for(CellDTO c : r){
                board[c.getRow()][c.getCol()] = c;
            }
        }
    }

    public CellDTO getCell(int row, int col){return board[row][col];}

    /**
     * Mirrors changeType from CellDTO while also clearing adjacently marked cells if c is cleared.
     * @param c
     * @param lClick
     * @param visible
     */
    public void changeType(CellDTO c, boolean lClick, boolean visible){
        c.changeType(lClick);
        if(c.getType() == CellType.EMPTY){
            clearAdjacent(c);
        }
        checkBoard(visible);
    }

    /**
     * Primary method for checking and setting if stars are in valid locations
     * @param visible
     */
    public void checkBoard(boolean visible){
        CellDTO current;
        boolean rowOut;
        boolean colOut;

        for(int i=0; i<gridSize; i++){
            for(int j=0; j<gridSize; j++){
                current = getCell(i,j);
                if(current.getType() == CellType.STAR || current.getType() == CellType.INVALID){
                    rowOut = checkRow(visible, current);
                    colOut = checkCol(visible, current);
                    if(!rowOut || !colOut){
                        current.setInvalid();
                    }
                    else{
                        current.setStar();
                    }
                    markAdjacent(current);
                }
            }
        }
        checkRegions();
    }


    private boolean checkRow(boolean visible, CellDTO current){
        int rowStars = 0;
        for(int i=0; i<gridSize; i++){
            if(getCell(current.getRow(), i).getType() == CellType.STAR || getCell(current.getRow(), i).getType() == CellType.INVALID){
                rowStars++;
            }
        }
        if(visible && rowStars>=numStars){
            for(int i=0; i<gridSize; i++){
                if(getCell(current.getRow(), i).getType() == CellType.INVALID || getCell(current.getRow(), i).getType() == CellType.AMARKER){
                    getCell(current.getRow(), i).setMarker();
                }
            }
        }
        if(visible && rowStars<numStars){
            for(int i=0; i<gridSize; i++){
                if(getCell(current.getRow(), i).getType() == CellType.VMARKER){
                    getCell(current.getRow(), i).setEmpty();
                }
            }
        }
        return rowStars <= numStars;
    }

    private boolean checkCol(boolean visible, CellDTO current){
        int colStars = 0;
        for(int i=0; i<gridSize; i++){
            if(getCell(i, current.getCol()).getType() == CellType.STAR || getCell(i, current.getCol()).getType() == CellType.INVALID){
                colStars++;
            }
        }
        if(visible && colStars>=numStars){
            for(int i=0; i<gridSize; i++){
                if(getCell(i, current.getCol()).getType() == CellType.EMPTY || getCell(i, current.getCol()).getType() == CellType.AMARKER){
                    getCell(i, current.getCol()).setMarker();
                }
            }
        }
        if(visible && colStars<numStars){
            for(int i=0; i<gridSize; i++){
                if(getCell(i, current.getCol()).getType() == CellType.VMARKER){
                    getCell(i, current.getCol()).setEmpty();
                }
            }
        }
        return colStars <= numStars;
    }

    private void checkRegions(){
        for(List<CellDTO> region : regions){
            int regionStars = 0;
            for(CellDTO cell : region){
                if(cell.getType() == CellType.STAR || cell.getType() == CellType.INVALID){
                    regionStars++;
                }
            }
            if(regionStars>numStars){
                for(CellDTO cell : region){
                    if(cell.getType() == CellType.STAR){
                        cell.setInvalid();
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
    private void markAdjacent(CellDTO current){
        int currentRow = current.getRow();
        int currentCol = current.getCol();
        CellDTO temp;

        for (int i = currentRow - 1; i < currentRow + 2; i++) {
            for (int j = currentCol - 1; j < currentCol + 2; j++) {
                if (i >= 0 && i < gridSize && j >= 0 && j < gridSize && !(i==currentRow && j==currentCol)) {
                    temp = getCell(i, j);
                    if (temp.getType() == CellType.EMPTY) {
                        temp.setAMarker();
                    }
                    if(temp.getType() == CellType.STAR || temp.getType() == CellType.INVALID){
                        current.setInvalid();
                    }
                }
            }
        }
    }

    private void clearAdjacent(CellDTO current) {
        int currentRow = current.getRow();
        int currentCol = current.getCol();
        CellDTO temp;

        for (int i = currentRow - 1; i < currentRow + 2; i++) {
            for (int j = currentCol - 1; j < currentCol + 2; j++) {
                if (i >= 0 && i < gridSize && j >= 0 && j < gridSize) {
                    temp = getCell(i, j);
                    if (temp.getType() == CellType.AMARKER) {
                        temp.setEmpty();
                    }
                }
            }
        }
    }

    public void printBoard(){
        for(int i=0; i<gridSize; i++){
            for(int j=0; j<gridSize; j++){
                System.out.printf("%10s", getCell(i,j).getType());
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "regions=" + regions +
                ", solution=" + solution +
                ", gridSize=" + gridSize +
                ", numStars=" + numStars +
                '}';
    }
}
