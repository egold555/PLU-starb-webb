package webb.client.logic.puzzle;

import java.awt.image.BufferedImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import webb.client.ui.constants.WebbImages;

/**
 * The type of the cell
 */
@AllArgsConstructor
public enum CellType {
    EMPTY((BufferedImage) null, "E"), // Empty cell
    STAR(WebbImages.PLAY_PUZZLE_GRID_STAR, "S"), // White star the player placed
    STAR_RED(WebbImages.PLAY_PUZZLE_GRID_STAR_RED, "R"), // Invalid star the player placed
    PLAYER_MARKER(WebbImages.PLAY_PUZZLE_GRID_INVALID_CELL, "I"), // X mark that the player placed

    // Brandon's internal markers.
    // I don't entirely remember what he explained these do, but I am added them to translate
    // The code to work with the gameboard
    AMARKER(WebbImages.PLAY_PUZZLE_GRID_AVMARKER, "A"),
    VMARKER(WebbImages.PLAY_PUZZLE_GRID_AVMARKER, "V"),
    INVALID(STAR_RED, "I")
    ;

    @Getter
    private final BufferedImage image;

    @Getter
    private final String letter;

    //Clone the cell type's image
    CellType(CellType cellType, String letter) {
        this.image = cellType.getImage();
        this.letter = letter;
    }
}

