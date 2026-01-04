package br.com.sudoku.view;

import br.com.sudoku.controller.SudokuController;
import br.com.sudoku.model.SudokuBoard;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class SudokuFrame extends JFrame {

    private JButton[][] cells = new JButton[9][9];
    private JLabel statusLabel;
    private JButton finishButton;

    private SudokuController controller;

    public SudokuFrame() {

        controller = new SudokuController(this);

        setTitle("Sudoku");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel("Preenchidos: 0 | Erros: 0");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(statusLabel, BorderLayout.SOUTH);
        add(createBoard(), BorderLayout.CENTER);
        add(createMenu(), BorderLayout.EAST);

        controller.startNewGame();
        refreshBoard();

        setVisible(true);
    }

    private JPanel createBoard() {
        JPanel panel = new JPanel(new GridLayout(9, 9));
        Font font = new Font("Arial", Font.BOLD, 20);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                JButton b = new JButton();
                b.setFont(font);
                b.setFocusPainted(false);
                b.setOpaque(true);

                int top = (i % 3 == 0) ? 4 : 1;
                int left = (j % 3 == 0) ? 4 : 1;
                int bottom = (i == 8) ? 4 : 1;
                int right = (j == 8) ? 4 : 1;

                b.setBorder(BorderFactory.createMatteBorder(
                        top, left, bottom, right, Color.BLACK
                ));

                final int r = i, c = j;
                b.addActionListener(e -> controller.selectCell(r, c));

                cells[i][j] = b;
                panel.add(b);
            }
        }
        return panel;
    }

    private JPanel createMenu() {
        JPanel menu = new JPanel(new GridLayout(5, 1, 10, 10));
        menu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton insert = new JButton("Inserir");
        JButton remove = new JButton("Remover");
        JButton note = new JButton("Rascunho");
        JButton newGame = new JButton("Nova Fase");
        finishButton = new JButton("Finalizar Jogo");

        insert.addActionListener(e -> {
            String v = JOptionPane.showInputDialog("Número 1-9:");
            if (v != null)
                controller.insertNumber(Integer.parseInt(v));
        });

        remove.addActionListener(e -> controller.removeNumber());

        note.addActionListener(e -> {
            String v = JOptionPane.showInputDialog("Rascunho 1-9:");
            if (v != null)
                controller.toggleNote(Integer.parseInt(v));
        });

        newGame.addActionListener(e -> {
            controller.startNewGame();
            refreshBoard();
        });

        finishButton.addActionListener(e -> controller.finishGame());

        menu.add(insert);
        menu.add(remove);
        menu.add(note);
        menu.add(newGame);
        menu.add(finishButton);

        return menu;
    }

    public void refreshBoard() {
        SudokuBoard m = controller.getModel();

        statusLabel.setText(
                "Preenchidos: " + m.countFilled() +
                        " | Erros: " + m.countErrors()
        );

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                JButton b = cells[i][j];
                int v = m.get(i, j);

                if (v != 0) {
                    b.setText(String.valueOf(v));
                    b.setFont(new Font("Arial", Font.BOLD, 20));
                } else {
                    Set<Integer> notes = m.getNotes(i, j);
                    if (!notes.isEmpty()) {
                        StringBuilder sb = new StringBuilder("<html><small>");
                        for (int n : notes) sb.append(n).append(" ");
                        sb.append("</small></html>");
                        b.setText(sb.toString());
                        b.setFont(new Font("Arial", Font.PLAIN, 11));
                    } else {
                        b.setText("");
                    }
                }

                b.setEnabled(!m.isFixed(i, j));
                b.setBackground(
                        m.isFixed(i, j)
                                ? new Color(220, 220, 220)
                                : Color.WHITE
                );
            }
        }
    }
}
