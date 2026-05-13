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
            e -> { 
                model.generateBoard(view.getDifficulty()); 
                view.updateBoard(model.getBoard()); 
            },
            e -> { 
                model.resetBoard(); 
                view.updateBoard(model.getBoard()); 
            },
            e -> { 
                if (model.isSolved()) {
                    view.showMessage("Congratulations! Sudoku Solved!");
                } else {
                    view.showMessage("Not solved yet or contains errors. Keep trying!");
                }
            },
            e -> handleSolve()
        );

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int r = i;
                final int c = j;
                view.addCellListener(i, j, new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        String text = view.getCellValue(r, c);
                        int val = 0;
                        if (text.matches("[1-9]")) {
                            val = Integer.parseInt(text);
                        }
                        
                        // Always update model so Check button works
                        model.placeNumber(r, c, val);
                        
                        if (val > 0) {
                            if (model.isMoveValid(r, c, val)) {
                                view.setCellColor(r, c, Constants.SUCCESS_COLOR);
                            } else {
                                view.setCellColor(r, c, Constants.ERROR_COLOR);
                            }
                        } else {
                            view.setCellColor(r, c, Constants.EDITABLE_CELL_COLOR);
                        }
                    }
                });
            }
        }
    }

    private void handleSolve() {
        // Reset non-fixed cells to ensure a clean start for the solver
        model.resetBoard();
        
        com.sudoku.service.SudokuSolver solver = new com.sudoku.service.SudokuSolver();
        int[][] boardArray = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boardArray[i][j] = model.getBoard()[i][j].getValue();
            }
        }
        
        if (solver.solve(boardArray)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (!model.getBoard()[i][j].isFixed()) {
                        model.getBoard()[i][j].setValue(boardArray[i][j]);
                    }
                }
            }
            view.updateBoard(model.getBoard());
        }
    }
}
