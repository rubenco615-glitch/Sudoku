package com.sudoku.util;

/**
 * Utility for input validation.
 */
public class InputValidator {
    public static boolean isValidInput(String input) {
        return input.matches("[1-9]");
    }
}
