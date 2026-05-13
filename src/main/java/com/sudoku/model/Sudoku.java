package com.sudoku.model;

import com.sudoku.service.SudokuGenerator;
import com.sudoku.util.Constants;

/**
 * Main model class for Sudoku game logic.
 */
public class Sudoku {
    private Cell[][] board;
    private final SudokuGenerator generator;

    public Sudoku() {
        this.generator = new SudokuGenerator();
        this.board = new Cell[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        initializeEmptyBoard();
    }

    private void initializeEmptyBoard() {
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                board[i][j] = new Cell(0, false);
            }
        }
    }

    public void generateBoard(String difficulty) {
        Difficulty level = Difficulty.valueOf(difficulty.toUpperCase());
        int[][] generatedValues = generator.generate(level);
        
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                int val = generatedValues[i][j];
                board[i][j] = new Cell(val, val != 0);
            }
        }
    }

    public boolean isMoveValid(int row, int col, int value) {
        if (value == 0) return true;

        // Row and Column check
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            if (i != col && board[row][i].getValue() == value) return false;
            if (i != row && board[i][col].getValue() == value) return false;
        }

        // 3x3 Block check
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if ((i != row || j != col) && board[i][j].getValue() == value) return false;
            }
        }
        return true;
    }

    public boolean placeNumber(int row, int col, int value) {
        if (board[row][col].isFixed()) return false;
        board[row][col].setValue(value);
        return true;
    }

    public void resetBoard() {
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                if (!board[i][j].isFixed()) {
                    board[i][j].setValue(0);
                }
            }
        }
    }

    public boolean isSolved() {
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                int val = board[i][j].getValue();
                if (val == 0 || !isMoveValid(i, j, val)) return false;
            }
        }
        return true;
    }

    public Cell[][] getBoard() {
        return board;
    }
}
