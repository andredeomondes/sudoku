package br.com.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame {

    private JButton[][] cells = new JButton[9][9];
    private int selectedRow = -1;
    private int selectedCol = -1;

    public SudokuFrame(String[] args) {

        setTitle("Sudoku");
        setSize(650, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createBoard(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createBoard() {
        JPanel board = new JPanel(new GridLayout(9, 9));
        Font font = new Font("Arial", Font.BOLD, 20);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                JButton cell = new JButton();
                cell.setFont(font);
                cell.setFocusPainted(false);
                cell.setBackground(Color.WHITE);

                int top = (row % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0) ? 3 : 1;
                int bottom = (row == 8) ? 3 : 1;
                int right = (col == 8) ? 3 : 1;

                cell.setBorder(BorderFactory.createMatteBorder(
                        top, left, bottom, right, Color.BLACK));

                final int r = row;
                final int c = col;
                cell.addActionListener(e -> selectCell(r, c));

                cells[row][col] = cell;
                board.add(cell);
            }
        }

        return board;
    }

    private void selectCell(int row, int col) {
        if (selectedRow != -1) {
            cells[selectedRow][selectedCol].setBackground(Color.WHITE);
        }

        selectedRow = row;
        selectedCol = col;

        cells[row][col].setBackground(new Color(184, 207, 229));
    }
}
