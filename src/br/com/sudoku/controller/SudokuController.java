package br.com.sudoku.controller;

import br.com.sudoku.model.SudokuBoard;
import br.com.sudoku.view.SudokuFrame;

public class SudokuController {

    private SudokuBoard model;
    private SudokuFrame view;

    public SudokuController(SudokuFrame view, String[] args) {
        this.view = view;
        this.model = new SudokuBoard();
        loadFixedNumbers(args);
    }

    private void loadFixedNumbers(String[] args) {
        // Formato esperado: linha,coluna,valor
        for (String arg : args) {
            String[] parts = arg.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            int value = Integer.parseInt(parts[2]);

            model.setFixed(row, col, value);
        }
    }

    public int getValue(int row, int col) {
        return model.get(row, col);
    }

    public boolean isFixed(int row, int col) {
        return model.isFixed(row, col);
    }
}
