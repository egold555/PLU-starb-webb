package webb.client.model.puzzle;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    /**
     * Creates a Puzzle from a JSON file
     * @param jsonFile the JSON file
     * @return the Puzzle object
     * @throws IOException if the file cannot be read
     */
    public static Puzzle fromJSON(File jsonFile) throws IOException {
        return new ObjectMapper().readValue(jsonFile, Puzzle.class);
    }

    public void loadBoard(){
        for(List<CellDTO> r : regions){
            for(CellDTO c : r){
                board[c.getRow()][c.getCol()] = c;
            }
        }
    }

    public CellDTO getCell(int row, int col){return board[row][col];}

    public void checkBoard(Boolean visible){
        for(int i=0; i<gridSize; i++) {
            checkRow(visible, i);
        }
        for(int i=0; i<gridSize; i++) {
            checkCol(visible, i);
        }
    }

    private void checkRow(Boolean visible, int row){
        int rowStars = 0;
        CellDTO current;
        String currentType;

        for(int i=0; i<gridSize; i++){
            current = getCell(row, i);
            currentType = current.getType();
            if(currentType.equals("STAR")||currentType.equals("RINVALID")||currentType.equals("CINVALID")){
                rowStars++;
                markStar(current);
            }
        }
        for(int i=0; i<gridSize; i++){
            current = getCell(row, i);
            currentType = current.getType();

            if(rowStars>=numStars) {
                if (currentType.equals("EMPTY") && visible) {
                    current.setMarker();
                }
            }
            if(rowStars>numStars){
                if (currentType.equals("STAR")) {
                    current.setInvalid("R");
                }
            }
            if(currentType.equals("VMARKER") && !visible){
                current.setEmpty();
            }
            if(currentType.equals("RINVALID") && rowStars<=numStars){
                current.setStar();
            }
        }
    }

    private void checkCol(Boolean visible, int col){
        int colStars = 0;
        CellDTO current;
        String currentType;

        for(int i=0; i<gridSize; i++){
            current = getCell(i, col);
            currentType = current.getType();
            if(currentType.equals("STAR")||currentType.equals("RINVALID")||currentType.equals("CINVALID")){
                colStars++;
                if(visible) {
                    markStar(current);
                }
            }
        }

        for(int i=0; i<gridSize; i++){
            current = getCell(i, col);
            currentType = current.getType();

            if(colStars>=numStars) {
                if (currentType.equals("EMPTY") && visible) {
                    current.setMarker();
                }
            }
            if(colStars>numStars){
                if(currentType.equals("STAR")){
                    current.setInvalid("C");
                }
            }
            if(currentType.equals("VMARKER") && !visible){
                current.setEmpty();
            }
            if(currentType.equals("CINVALID") && colStars<=numStars){
                current.setStar();
            }
        }
    }

    private void markStar(CellDTO current){
        int currentRow = current.getRow();
        int currentCol = current.getCol();
        CellDTO temp;

        if(currentRow>0){
            temp = getCell(currentRow-1, currentCol);
            if(temp.getType().equals("EMPTY")){temp.setMarker();}
            if(currentCol>0){
                temp = getCell(currentRow-1, currentCol-1);
                if(temp.getType().equals("EMPTY")){temp.setMarker();}
            }
            if(currentCol<gridSize-1){
                temp = getCell(currentRow-1, currentCol+1);
                if(temp.getType().equals("EMPTY")){temp.setMarker();}
            }
        }
        if(currentRow<gridSize-1){
            temp = getCell(currentRow+1, currentCol);
            if(temp.getType().equals("EMPTY")){temp.setMarker();}
            if(currentCol>0){
                temp = getCell(currentRow+1, currentCol-1);
                if(temp.getType().equals("EMPTY")){temp.setMarker();}
            }
            if(currentCol<gridSize-1){
                temp = getCell(currentRow+1, currentCol+1);
                if(temp.getType().equals("EMPTY")){temp.setMarker();}
            }
        }
        if(currentCol>0){
            temp = getCell(currentRow, currentCol-1);
            if(temp.getType().equals("EMPTY")){temp.setMarker();}
        }
        if(currentCol<gridSize-1){
            temp = getCell(currentRow, currentCol+1);
            if(temp.getType().equals("EMPTY")){temp.setMarker();}
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
