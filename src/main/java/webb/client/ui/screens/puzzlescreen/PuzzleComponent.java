package webb.client.ui.screens.puzzlescreen;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import webb.client.model.puzzle.CellDTO;
import webb.client.ui.constants.WebbColors;
import webb.client.ui.screens.puzzlescreen.Cell.CellType;
import webb.client.model.puzzle.PuzzleDTO;

public class PuzzleComponent extends JComponent {

    private static final int MARGIN = 15;
    private int gridSize = 10;
    private int numStars = 0;

    //COL, ROW
    private Cell[][] board;

    private List<List<Cell>> regions;

    public PuzzleComponent() {
        //this.setBorder( new LineBorder( Color.BLUE, 2, true ));
        this.setOpaque(false);

        //on click event
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                final int x = evt.getX();
                final int y = evt.getY();

                final int btn = evt.getButton();
                final boolean leftClick = btn == MouseEvent.BUTTON1;
                final boolean rightClick = btn == MouseEvent.BUTTON3;

                //We don't want a middle click for example
                if(!leftClick && !rightClick) return;

                //if mouse is in a square
                for(int row = 0; row < gridSize; row++ ) {
                    for(int col = 0; col < gridSize; col++ ) {
                        Cell cell = getCell(col, row);
                        if( cell.isInside(x, y) ) {
                            cell.onClick(rightClick);
                        }
                    }
                }
            }
        });
    }

    /**
     * Sets the size of the grid, and resets the cells
     * @param size Size of the grid
     */
    private void setGridSize(int size) {
        gridSize = size;
        board = null;
        board = new Cell[gridSize][gridSize];
        for(int row = 0; row < gridSize; row++ ) {
            for(int col = 0; col < gridSize; col++ ) {
                board[col][row] = new Cell( col, row );
            }
        }

        //if(repaint) {
        this.repaint();
        //}
    }

    /**
     * Set the puzzle to be displayed
     * @param puzzle Puzzle to be displayed
     */
    public void setPuzzle(PuzzleDTO puzzle) {

        //Set the puzzle size & reset the cells
        this.setGridSize(puzzle.getGridSize());

        this.numStars = puzzle.getNumStars();

        regions = new ArrayList<>();

        //Set the cell groups
        //Loop through every region, and set the group of every cell in that region to the region index
        for(int i = 0; i < puzzle.getRegions().size(); i++ ) {
            List<CellDTO> listOfCells = puzzle.getRegions().get(i);
            List<Cell> tmp = new ArrayList<>();
            for(CellDTO cellDTO : listOfCells) {
                Cell cell = getCell(cellDTO.getCol(), cellDTO.getRow());
                cell.setGroup(i);
                tmp.add(cell);
            }
            regions.add(tmp);
        }

        //Set the solution boolean for every cell that is in the solution
        for(CellDTO cellDTO : puzzle.getSolution()) {
            Cell cell = getCell(cellDTO.getCol(), cellDTO.getRow());
            cell.setSolutionStar();

            //TODO: Testing - Show the solution
            cell.setType(CellType.STAR);
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
                Cell cell = getCell(col, row);


                //North
                if(row == 0 || getCell(col, row - 1).getGroup() != cell.getGroup()) {
                    cell.setWall(Cell.WALL_NORTH);
                }

                //East
                if(col == gridSize - 1 || getCell(col + 1, row).getGroup() != cell.getGroup()) {
                    cell.setWall(Cell.WALL_EAST);
                }

                //South
                if(row == gridSize - 1 || getCell(col, row + 1).getGroup() != cell.getGroup()) {
                    cell.setWall(Cell.WALL_SOUTH);
                }

                //West
                if(col == 0 || getCell(col - 1, row).getGroup() != cell.getGroup()) {
                    cell.setWall(Cell.WALL_WEST);
                }

            }
        }

        //repaint
        this.repaint();
    }

    /**
     * Mirrors changeType from CellDTO while also clearing adjacently marked cells if c is cleared.
     * @param c
     * @param lClick
     * @param visible
     */
    public void changeType(Cell c, CellType type, boolean visible){
        c.setType(type);
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
        Cell current;
        boolean rowOut;
        boolean colOut;

        for(int i=0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
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


    private boolean checkRow(boolean visible, Cell current){
        int rowStars = 0;
        for(int i = 0; i < gridSize; i++){
            if(getCell(current.getRow(), i).getType() == CellType.STAR || getCell(current.getRow(), i).getType() == CellType.INVALID){
                rowStars++;
            }
        }
        if(visible && rowStars>=numStars){
            for(int i = 0; i < gridSize; i++){
                if(getCell(current.getRow(), i).getType() == CellType.EMPTY || getCell(current.getRow(), i).getType() == CellType.AMARKER){
                    getCell(current.getRow(), i).setMarker();
                }
            }
        }
        if(visible && rowStars<numStars){
            for(int i = 0; i < gridSize; i++){
                if(getCell(current.getRow(), i).getType() == CellType.VMARKER){
                    getCell(current.getRow(), i).setEmpty();
                }
            }
        }
        return rowStars <= numStars;
    }

    private boolean checkCol(boolean visible, Cell current){
        int colStars = 0;
        for(int i=0; i < gridSize; i++){
            if(getCell(i, current.getCol()).getType() == CellType.STAR || getCell(i, current.getCol()).getType() == CellType.INVALID){
                colStars++;
            }
        }
        if(visible && colStars>=numStars){
            for(int i=0; i < gridSize; i++){
                if(getCell(i, current.getCol()).getType() == CellType.EMPTY || getCell(i, current.getCol()).getType() == CellType.AMARKER){
                    getCell(i, current.getCol()).setMarker();
                }
            }
        }
        if(visible && colStars<numStars){
            for(int i=0; i < gridSize; i++){
                if(getCell(i, current.getCol()).getType() == CellType.VMARKER){
                    getCell(i, current.getCol()).setEmpty();
                }
            }
        }
        return colStars <= numStars;
    }

    private void checkRegions(){
        for(List<Cell> region : regions){
            int regionStars = 0;
            for(Cell cell : region){
                if(cell.getType() == CellType.STAR || cell.getType() == CellType.INVALID){
                    regionStars++;
                }
            }
            if(regionStars > numStars){
                for(Cell cell : region){
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
    private void markAdjacent(Cell current){
        int currentRow = current.getRow();
        int currentCol = current.getCol();
        Cell temp;

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

    private void clearAdjacent(Cell current) {
        int currentRow = current.getRow();
        int currentCol = current.getCol();
        Cell temp;

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
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                System.out.printf("%10s", getCell(i,j).getType());
            }
            System.out.println();
        }
    }

    /**
     * Paints the component
     * @param g the Graphics object
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //do some fun math to calculate all the positions we need based on the component size
        final int width = this.getWidth();
        final int height = this.getHeight();

        final double centerX = width / 2D;
        final double centerY = height / 2D;
        final double gridSize = Math.min(width, height) - (MARGIN * 2 );
        final double cellSize = gridSize / this.gridSize;

        final double gridLeft = centerX - gridSize / 2D;
        final double gridTop = centerY - gridSize / 2D;

        //draw background
        g2d.setColor( WebbColors.c34 );
        g2d.fillRect( (int) gridLeft, (int) gridTop, (int) gridSize, (int) gridSize);

        g2d.setColor(WebbColors.c70 );
        g2d.setStroke( new BasicStroke( 3 ));

        // Draw the grid
        Line2D.Double line = new Line2D.Double();
        for(int i = 0; i < this.gridSize + 1; i++ ) {
            line.x1 = gridLeft;
            line.y1 = gridTop + i * cellSize;
            line.x2 = gridLeft + cellSize * this.gridSize;
            line.y2 = line.y1;
            g2d.draw( line );
        }

        for(int i = 0; i < this.gridSize + 1; i++ ) {
            line.x1 = gridLeft + i * cellSize;
            line.y1 = gridTop;
            line.x2 = line.x1;
            line.y2 = gridTop + cellSize * this.gridSize;
            g2d.draw( line );
        }

        //draw border
        g2d.setColor( WebbColors.c90 );
        g2d.setStroke( new BasicStroke( 5 ));
        g2d.drawRect( (int) gridLeft, (int) gridTop, (int) gridSize, (int) gridSize);

        //Update the position of the cell objects when we resize
        // Make sure to call this BEFORE drawing the images
        for(int row = 0; row < this.gridSize; row++ ) {
            for(int col = 0; col < this.gridSize; col++ ) {
                Cell cell = getCell(col, row);
                cell.setBounds( (gridLeft + col * cellSize), (gridTop + row * cellSize), cellSize, cellSize );
            }
        }

        //draw all the cells
        for(int row = 0; row < this.gridSize; row++ ) {
            for(int col = 0; col < this.gridSize; col++ ) {
                Cell cell = getCell(col, row);
                cell.draw(g2d);
            }
        }

    }

    /**
     * Get a cell at a specific position
     * @param col Column of the cell
     * @param row Row of the cell
     * @return The cell at the specified position
     * @throws IllegalArgumentException if the position is invalid
     */
    public Cell getCell(int col, int row) {
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
