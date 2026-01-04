package br.com.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame {

    public StartScreen() {
        setTitle("Sudoku");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("SUDOKU", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));

        JButton start = new JButton("Iniciar Jogo");
        start.setFont(new Font("Arial", Font.BOLD, 18));

        start.addActionListener(e -> {
            dispose();
            new SudokuFrame();
        });

        add(title, BorderLayout.CENTER);
        add(start, BorderLayout.SOUTH);

        setVisible(true);
    }
}
