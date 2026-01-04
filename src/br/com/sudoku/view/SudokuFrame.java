package br.com.sudoku.view;

import br.com.sudoku.controller.SudokuController;
import br.com.sudoku.model.SudokuBoard;

import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame {

    private JButton[][] cells = new JButton[9][9];
    private SudokuController controller;

    public SudokuFrame() {

        setTitle("Sudoku");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        controller = new SudokuController(this);

        add(createBoard(), BorderLayout.CENTER);
        add(createMenu(), BorderLayout.EAST);

        controller.startNewGame();
        refreshBoard();

        setVisible(true);
    }

    private JPanel createBoard() {
        JPanel panel = new JPanel(new GridLayout(9, 9));
        Font font = new Font("Arial", Font.BOLD, 20);

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {

                JButton b = new JButton();
                b.setFont(font);
                final int r = i, c = j;

                b.addActionListener(e -> controller.selectCell(r, c));

                cells[i][j] = b;
                panel.add(b);
            }
        return panel;
    }

    private JPanel createMenu() {
        JPanel menu = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton insert = new JButton("Inserir");
        JButton remove = new JButton("Remover");
        JButton finish = new JButton("Finalizar");

        insert.addActionListener(e -> {
            String v = JOptionPane.showInputDialog("Número 1-9:");
            if (v != null)
                controller.insertNumber(Integer.parseInt(v));
        });

        remove.addActionListener(e -> controller.removeNumber());
        finish.addActionListener(e -> controller.finishGame());

        menu.add(insert);
        menu.add(remove);
        menu.add(finish);

        return menu;
    }

    public void refreshBoard() {
        SudokuBoard m = controller.getModel();

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                int v = m.get(i, j);
                JButton b = cells[i][j];

                b.setText(v == 0 ? "" : String.valueOf(v));
                b.setEnabled(!m.isFixed(i, j));
                b.setBackground(m.isFixed(i, j)
                        ? new Color(220, 220, 220)
                        : Color.WHITE);
            }
    }
}
