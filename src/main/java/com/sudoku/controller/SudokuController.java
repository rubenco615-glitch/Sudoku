package com.sudoku.controller;

import com.sudoku.model.Sudoku;
import com.sudoku.view.SudokuGUI;
import com.sudoku.util.Constants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Controller to handle interaction between Model and View.
 */
public class SudokuController {
    private final Sudoku model;
    private final SudokuGUI view;

    public SudokuController(Sudoku model, SudokuGUI view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        view.addListeners(
            e -> { model.generateBoard(view.getDifficulty()); view.updateBoard(model.getBoard()); },
            e -> { model.resetBoard(); view.updateBoard(model.getBoard()); },
            e -> { if (model.isSolved()) view.showMessage("Sudoku Solved!"); else view.showMessage("Not solved yet!"); },
            e -> { /* Solve logic implemented in service, used via model in a real app */ }
        );

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int r = i;
                final int c = j;
                view.addCellListener(i, j, new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        String text = view.getCellValue(r, c);
                        if (text.matches("[1-9]")) {
                            int val = Integer.parseInt(text);
                            if (model.isMoveValid(r, c, val)) {
                                model.placeNumber(r, c, val);
                                view.setCellColor(r, c, Constants.SUCCESS_COLOR);
                            } else {
                                view.setCellColor(r, c, Constants.ERROR_COLOR);
                            }
                        } else if (text.isEmpty()) {
                            model.placeNumber(r, c, 0);
                            view.setCellColor(r, c, Constants.EDITABLE_CELL_COLOR);
                        }
                    }
                });
            }
        }
    }
}
