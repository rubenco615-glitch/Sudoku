package com.sudoku.service;

import com.sudoku.model.Difficulty;
import java.util.Random;

/**
 * Service to generate Sudoku puzzles.
 */
public class SudokuGenerator {
    private final SudokuSolver solver = new SudokuSolver();
    private final Random random = new Random();

    public int[][] generate(Difficulty difficulty) {
        int[][] board = new int[9][9];
        solver.solve(board);
        
        int toRemove = switch(difficulty) {
            case EASY -> 35;
            case MEDIUM -> 50;
            case HARD -> 65;
        };

        while (toRemove > 0) {
            int r = random.nextInt(9);
            int c = random.nextInt(9);
            if (board[r][c] != 0) {
                board[r][c] = 0;
                toRemove--;
            }
        }
        return board;
    }
}
