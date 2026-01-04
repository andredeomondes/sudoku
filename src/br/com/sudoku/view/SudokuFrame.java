package br.com.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame {

    public SudokuFrame(String[] args) {

        setTitle("Sudoku");
        setSize(600, 600);
        setLocationRelativeTo(null); // centraliza na tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Sudoku", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 28));
        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
}
