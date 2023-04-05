package webb.client.logic.puzzle;

import java.awt.image.BufferedImage;
import webb.client.ui.constants.WebbImages;

/**
 * The type of the cell
 */
public enum CellType {
    EMPTY((BufferedImage) null), // Empty cell
    STAR(WebbImages.PLAY_PUZZLE_GRID_STAR), // White star the player placed
    STAR_RED(WebbImages.PLAY_PUZZLE_GRID_STAR_RED), // Invalid star the player placed
    PLAYER_MARKER(WebbImages.PLAY_PUZZLE_GRID_INVALID_CELL), // X mark that the player placed

    // Brandon's internal markers.
    // I don't entirely remember what he explained these do, but I am added them to translate
    // The code to work with the gameboard
    AMARKER(PLAYER_MARKER),
    VMARKER(PLAYER_MARKER),
    INVALID(STAR_RED)
    ;

    private final BufferedImage img;

    //Clone the cell type's image
    CellType(CellType cellType) {
        this.img = cellType.getImage();
    }

    //Create a new cell type
    CellType(BufferedImage image) {
        this.img = image;
    }

    public BufferedImage getImage() {
        return img;
    }
}

