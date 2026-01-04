package br.com.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame {

    private JButton[][] cells = new JButton[9][9];

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

                cells[row][col] = cell;
                board.add(cell);
            }
        }

        return board;
    }
}
