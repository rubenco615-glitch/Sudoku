package com.sudoku;

import com.sudoku.controller.SudokuController;
import com.sudoku.model.Sudoku;
import com.sudoku.view.SudokuGUI;
import javax.swing.SwingUtilities;

/**
 * Entry point for the application.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Sudoku model = new Sudoku();
            SudokuGUI view = new SudokuGUI();
            new SudokuController(model, view);
            view.setVisible(true);
        });
    }
}
