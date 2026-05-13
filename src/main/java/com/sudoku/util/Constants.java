package com.sudoku.util;

import java.awt.Color;

/**
 * Global constants for the application.
 */
public final class Constants {
    private Constants() {}

    public static final int BOARD_SIZE = 9;
    public static final int GRID_SIZE = 3;

    // GUI Themes
    public static final Color FIXED_CELL_COLOR = new Color(230, 230, 230);
    public static final Color EDITABLE_CELL_COLOR = Color.WHITE;
    public static final Color ERROR_COLOR = new Color(255, 200, 200);
    public static final Color SUCCESS_COLOR = new Color(200, 255, 200);
}
