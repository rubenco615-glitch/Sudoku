package com.sudoku.view;

import com.sudoku.model.Cell;
import com.sudoku.util.Constants;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * Main GUI for the Sudoku game.
 */
public class SudokuGUI extends JFrame {
    /** Text fields representing the 9x9 grid cells. */
    private final JTextField[][] fields = new JTextField[9][9];
    
    /** Control buttons for game actions. */
    private JButton newGameBtn, resetBtn, checkBtn, solveBtn, playBtn;
    
    /** Dropdown for selecting game difficulty. */
    private JComboBox<String> difficultyCombo;
    
    /** Main container panel using CardLayout to switch screens. */
    private JPanel mainContainer;
    
    /** Layout manager for switching between Welcome and Game panels. */
    private CardLayout cardLayout;

    public SudokuGUI() {
        setTitle("Sudoku Master - MVC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        initWelcomePanel();
        initGamePanel();
        
        add(mainContainer);
        pack();
        setLocationRelativeTo(null);
    }

    private void initWelcomePanel() {
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(new Color(40, 44, 52));
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel titleLabel = new JLabel("SUDOKU");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 72));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0);
        welcomePanel.add(titleLabel, gbc);

        playBtn = new JButton("JUGAR");
        playBtn.setFont(new Font("Arial", Font.BOLD, 24));
        playBtn.setFocusPainted(false);
        playBtn.setBackground(new Color(97, 218, 251));
        playBtn.setForeground(Color.BLACK);
        playBtn.setPreferredSize(new Dimension(200, 60));
        gbc.gridy = 1;
        welcomePanel.add(playBtn, gbc);

        playBtn.addActionListener(e -> cardLayout.show(mainContainer, "GAME"));

        mainContainer.add(welcomePanel, "WELCOME");
    }

    private void initGamePanel() {
        JPanel gamePanel = new JPanel(new BorderLayout());
        
        // Grid Panel
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        gridPanel.setBackground(Constants.BACKGROUND_COLOR);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields[i][j] = new JTextField(2);
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                fields[i][j].setFont(new Font("SansSerif", Font.BOLD, 18));
                fields[i][j].setForeground(Constants.TEXT_COLOR);
                fields[i][j].setCaretColor(Constants.TEXT_COLOR);
                
                int top = (i % 3 == 0) ? 3 : 1;
                int left = (j % 3 == 0) ? 3 : 1;
                int bottom = (i == 8) ? 3 : 1;
                int right = (j == 8) ? 3 : 1;
                fields[i][j].setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));
                
                gridPanel.add(fields[i][j]);
            }
        }
        gamePanel.add(gridPanel, BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Constants.PANEL_COLOR);
        difficultyCombo = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});
        newGameBtn = new JButton("New Game");
        resetBtn = new JButton("Reset");
        checkBtn = new JButton("Check");
        solveBtn = new JButton("Solve");

        controlPanel.add(difficultyCombo);
        controlPanel.add(newGameBtn);
        controlPanel.add(resetBtn);
        controlPanel.add(checkBtn);
        controlPanel.add(solveBtn);
        gamePanel.add(controlPanel, BorderLayout.SOUTH);

        mainContainer.add(gamePanel, "GAME");
    }

    public void updateBoard(Cell[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int val = board[i][j].getValue();
                fields[i][j].setText(val == 0 ? "" : String.valueOf(val));
                fields[i][j].setEditable(!board[i][j].isFixed());
                fields[i][j].setBackground(board[i][j].isFixed() ? Constants.FIXED_CELL_COLOR : Constants.EDITABLE_CELL_COLOR);
                fields[i][j].setForeground(board[i][j].isFixed() ? Constants.FIXED_TEXT_COLOR : Constants.TEXT_COLOR);
                
                // Limpiar cualquier resaltado previo
                fields[i][j].setBorder(new MatteBorder(
                    (i % 3 == 0) ? 3 : 1, (j % 3 == 0) ? 3 : 1,
                    (i == 8) ? 3 : 1, (j == 8) ? 3 : 1, Color.BLACK));
            }
        }
    }

    public void addSelectionHighlight(int row, int col) {
        String target = fields[row][col].getText();
        if (target.isEmpty()) return;
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (fields[i][j].getText().equals(target)) {
                    fields[i][j].setBorder(BorderFactory.createLineBorder(new Color(97, 218, 251), 2));
                } else {
                    fields[i][j].setBorder(new MatteBorder(
                        (i % 3 == 0) ? 3 : 1, (j % 3 == 0) ? 3 : 1,
                        (i == 8) ? 3 : 1, (j == 8) ? 3 : 1, Color.BLACK));
                }
            }
        }
    }

    public void updateCell(int row, int col, String text) {
        fields[row][col].setText(text);
    }

    public void addListeners(ActionListener newGame, ActionListener reset, ActionListener check, ActionListener solve) {
        newGameBtn.addActionListener(newGame);
        resetBtn.addActionListener(reset);
        checkBtn.addActionListener(check);
        solveBtn.addActionListener(solve);
    }

    public void addCellListener(int row, int col, KeyListener kl) {
        fields[row][col].addKeyListener(kl);
    }

    public void addCellFocusListener(int row, int col, java.awt.event.FocusListener fl) {
        fields[row][col].addFocusListener(fl);
    }

    public String getDifficulty() { return (String) difficultyCombo.getSelectedItem(); }
    public String getCellValue(int r, int c) { return fields[r][c].getText(); }
    public void setCellColor(int r, int c, Color color) { fields[r][c].setBackground(color); }
    
    public void showMessage(String msg) { JOptionPane.showMessageDialog(this, msg); }
}
