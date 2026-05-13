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

                        if (!text.isEmpty()) {
                            String lastChar = text.substring(text.length() - 1);
                            if (lastChar.matches("[1-9]")) {
                                val = Integer.parseInt(lastChar);
                                view.updateCell(r, c, lastChar);
                            } else {
                                view.updateCell(r, c, "");
                            }
                        }

                        model.placeNumber(r, c, val);
                        validateAllCells(); // Re-validar todo el tablero al instante
                    }
                });

                // Resaltar números iguales al hacer clic (Corregido)
                view.addCellFocusListener(r, c, new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent e) {
                        view.addSelectionHighlight(r, c);
                    }
                });
            }
        }
    }

    private void validateAllCells() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = model.getBoard()[i][j].getValue();
                boolean isFixed = model.getBoard()[i][j].isFixed();
                
                if (val == 0) {
                    view.setCellColor(i, j, Constants.EDITABLE_CELL_COLOR);
                } else {
                    if (model.isMoveValid(i, j, val)) {
                        view.setCellColor(i, j, isFixed ? Constants.FIXED_CELL_COLOR : Constants.SUCCESS_COLOR);
                    } else {
                        // Si es fijo, no lo ponemos en rojo (es la "verdad"), pero sí al usuario que choca
                        if (isFixed) {
                            view.setCellColor(i, j, Constants.FIXED_CELL_COLOR);
                        } else {
                            view.setCellColor(i, j, Constants.ERROR_COLOR);
                        }
                    }
                }
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
        } else {
            view.showMessage("No solution exists for the current board! Check for mistakes or reset.");
        }
    }
}
