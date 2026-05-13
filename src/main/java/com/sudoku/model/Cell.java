package com.sudoku.model;

/**
 * Represents a single cell in the Sudoku grid.
 */
public class Cell {
    private int value;
    private final boolean fixed;

    public Cell(int value, boolean fixed) {
        this.value = value;
        this.fixed = fixed;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (!fixed) {
            this.value = value;
        }
    }

    public boolean isFixed() {
        return fixed;
    }
}
