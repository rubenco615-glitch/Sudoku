package com.sudoku.util;

/**
 * Utility for input validation.
 */
/**
 * Utility class for Sudoku-specific input validation.
 * Ensures that all user entries and logic coordinates are within valid bounds.
 */
public class InputValidator {

    /**
     * Checks if a given string is a valid Sudoku number (1-9).
     *
     * @param input the string to validate
     * @return true if the input is a single digit between 1 and 9, false otherwise
     */
    public static boolean isValidNumber(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("[1-9]");
    }

    /**
     * Validates if the coordinates are within the 9x9 board boundaries.
     *
     * @param row the row index
     * @param col the column index
     * @return true if coordinates are 0-8, false otherwise
     */
    public static boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < 9 && col >= 0 && col < 9;
    }

    /**
     * Checks if a numeric value is valid for a Sudoku cell (0-9, where 0 is empty).
     *
     * @param value the integer value to check
     * @return true if valid, false otherwise
     */
    public static boolean isValidValue(int value) {
        return value >= 0 && value <= 9;
    }
}
