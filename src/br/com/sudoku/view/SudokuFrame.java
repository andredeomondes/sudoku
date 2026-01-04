package br.com.sudoku.view;

import br.com.sudoku.controller.SudokuController;

import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame {

    private JButton[][] cells = new JButton[9][9];
    private int selectedRow = -1;
    private int selectedCol = -1;

    private SudokuController controller;

    public SudokuFrame(String[] args) {

        controller = new SudokuController(this, args);

        setTitle("Sudoku");
        setSize(750, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createBoard(), BorderLayout.CENTER);
        add(createMenu(), BorderLayout.EAST);

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

                int top = (row % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0) ? 3 : 1;
                int bottom = (row == 8) ? 3 : 1;
                int right = (col == 8) ? 3 : 1;

                cell.setBorder(BorderFactory.createMatteBorder(
                        top, left, bottom, right, Color.BLACK));

                int value = controller.getValue(row, col);
                if (value != 0) {
                    cell.setText(String.valueOf(value));
                    cell.setEnabled(false);
                    cell.setBackground(new Color(220, 220, 220));
                } else {
                    cell.setBackground(Color.WHITE);
                }

                final int r = row;
                final int c = col;
                cell.addActionListener(e -> selectCell(r, c));

                cells[row][col] = cell;
                board.add(cell);
            }
        }
        return board;
    }

    private JPanel createMenu() {
        JPanel menu = new JPanel(new GridLayout(2, 1, 5, 5));
        menu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton insert = new JButton("Inserir número");
        JButton remove = new JButton("Remover número");

        insert.addActionListener(e -> controller.insertNumber());
        remove.addActionListener(e -> controller.removeNumber());

        menu.add(insert);
        menu.add(remove);

        return menu;
    }

    private void selectCell(int row, int col) {
        if (selectedRow != -1 && !controller.isFixed(selectedRow, selectedCol)) {
            cells[selectedRow][selectedCol].setBackground(Color.WHITE);
        }

        selectedRow = row;
        selectedCol = col;
        controller.selectCell(row, col);

        if (!controller.isFixed(row, col)) {
            cells[row][col].setBackground(new Color(184, 207, 229));
        }
    }

    public void updateCell(int row, int col, int value) {
        if (value == 0) {
            cells[row][col].setText("");
        } else {
            cells[row][col].setText(String.valueOf(value));
        }
    }
}
