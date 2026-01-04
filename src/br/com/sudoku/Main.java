package br.com.sudoku;

import br.com.sudoku.view.SudokuFrame;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new SudokuFrame();
        });
    }
}
