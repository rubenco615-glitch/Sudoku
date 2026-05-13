package com.sudoku.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the SudokuSolver service.
 */
public class SudokuSolverTest {
    private final SudokuSolver solver = new SudokuSolver();

    @Test
    void testSolveEmptyBoard() {
        int[][] board = new int[9][9];
        assertTrue(solver.solve(board), "Solver should solve an empty board");
        
        // Verify completion
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertNotEquals(0, board[i][j]);
            }
        }
    }

    @Test
    void testSolveComplexBoard() {
        // Classic easy Sudoku puzzle
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        assertTrue(solver.solve(board), "Solver should solve a partial board");
        assertEquals(4, board[0][2]);
    }
}
