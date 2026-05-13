package com.sudoku.service;

import com.sudoku.model.Difficulty;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service to generate random Sudoku puzzles.
 */
public class SudokuGenerator {
    private final Random random = new Random();

    public int[][] generate(Difficulty difficulty) {
        int[][] board = new int[9][9];
        fillRandomly(board);
        
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

    private boolean fillRandomly(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    List<Integer> nums = new ArrayList<>();
                    for (int i = 1; i <= 9; i++) nums.add(i);
                    Collections.shuffle(nums);
                    
                    for (int num : nums) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (fillRandomly(board)) return true;
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        return true;
    }
}
