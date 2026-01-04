package br.com.sudoku.controller;

import br.com.sudoku.model.SudokuBoard;
import br.com.sudoku.view.SudokuFrame;

import javax.swing.*;

public class SudokuController {

    private SudokuBoard model;
    private SudokuFrame view;

    private int selectedRow = -1;
    private int selectedCol = -1;

    public SudokuController(SudokuFrame view, String[] args) {
        this.view = view;
        this.model = new SudokuBoard();
        loadFixedNumbers(args);
    }

    private void loadFixedNumbers(String[] args) {
        for (String arg : args) {
            String[] parts = arg.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            int value = Integer.parseInt(parts[2]);
            model.setFixed(row, col, value);
        }
    }

    public void selectCell(int row, int col) {
        selectedRow = row;
        selectedCol = col;
    }

    public int getValue(int row, int col) {
        return model.get(row, col);
    }

    public boolean isFixed(int row, int col) {
        return model.isFixed(row, col);
    }


    public void insertNumber() {
        if (selectedRow == -1 || model.isFixed(selectedRow, selectedCol)) {
            JOptionPane.showMessageDialog(view, "Selecione uma célula válida.");
            return;
        }

        String input = JOptionPane.showInputDialog(view, "Número (1-9):");
        if (input == null) return;

        int value = Integer.parseInt(input);
        model.set(selectedRow, selectedCol, value);
        view.updateCell(selectedRow, selectedCol, value);
    }

    public void removeNumber() {
        if (selectedRow == -1 || model.isFixed(selectedRow, selectedCol)) {
            JOptionPane.showMessageDialog(view, "Número fixo não pode ser removido.");
            return;
        }

        model.set(selectedRow, selectedCol, 0);
        view.updateCell(selectedRow, selectedCol, 0);
    }
}
