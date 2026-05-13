package com.sudoku.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Sudoku model.
 */
public class SudokuTest {
    private Sudoku sudoku;

    @BeforeEach
    void setUp() {
        sudoku = new Sudoku();
    }

    @Test
    void testMoveValid() {
        // In an empty board, placing a 5 at 0,0 should be valid
        assertTrue(sudoku.isMoveValid(0, 0, 5));
    }

    @Test
    void testMoveInvalidRow() {
        sudoku.placeNumber(0, 0, 5);
        // Placing another 5 in the same row (0,5) should be invalid
        assertFalse(sudoku.isMoveValid(0, 5, 5));
    }

    @Test
    void testMoveInvalidColumn() {
        sudoku.placeNumber(0, 0, 5);
        // Placing another 5 in the same column (5,0) should be invalid
        assertFalse(sudoku.isMoveValid(5, 0, 5));
    }

    @Test
    void testPlaceFixedCell() {
        // We simulate a fixed cell (usually set by generateBoard)
        sudoku.getBoard()[0][0] = new Cell(5, true);
        assertFalse(sudoku.placeNumber(0, 0, 9), "Should not edit a fixed cell");
        assertEquals(5, sudoku.getBoard()[0][0].getValue());
    }

    @Test
    void testResetBoard() {
        sudoku.getBoard()[0][0] = new Cell(5, true);
        sudoku.placeNumber(0, 1, 9);
        sudoku.resetBoard();
        
        assertEquals(5, sudoku.getBoard()[0][0].getValue(), "Fixed cell must remain");
        assertEquals(0, sudoku.getBoard()[0][1].getValue(), "Editable cell must be cleared");
    }

    @Test
    void testIsSolved() {
        // An empty board is not solved
        assertFalse(sudoku.isSolved());
    }
}
