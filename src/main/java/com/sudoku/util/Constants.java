package com.sudoku.util;

import java.awt.Color;

/**
 * Global constants for the application.
 */
public final class Constants {
    private Constants() {}

    public static final int BOARD_SIZE = 9;
    public static final int GRID_SIZE = 3;

    // Dark Theme Colors
    public static final Color BACKGROUND_COLOR = new Color(30, 30, 30);
    public static final Color PANEL_COLOR = new Color(45, 45, 48);
    public static final Color FIXED_CELL_COLOR = new Color(60, 60, 60);
    public static final Color EDITABLE_CELL_COLOR = new Color(40, 40, 40);
    public static final Color TEXT_COLOR = Color.WHITE;
    public static final Color ERROR_COLOR = new Color(150, 50, 50);
    public static final Color SUCCESS_COLOR = new Color(50, 150, 50);
}
