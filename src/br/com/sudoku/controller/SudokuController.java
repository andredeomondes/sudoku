package br.com.sudoku.controller;

import br.com.sudoku.model.*;
import br.com.sudoku.view.SudokuFrame;

public class SudokuController {

    private SudokuBoard model = new SudokuBoard();
    private SudokuFrame view;

    private int selectedRow = -1;
    private int selectedCol = -1;

    public SudokuController(SudokuFrame view) {
        this.view = view;
    }

    public void startNewGame() {
        int holes = 40;
        int[][] puzzle = SudokuGenerator.generatePuzzle(holes);
        model.load(puzzle);
        selectedRow = -1;
    }

    public SudokuBoard getModel() {
        return model;
    }

    public void selectCell(int r, int c) {
        selectedRow = r;
        selectedCol = c;
    }

    public void insertNumber(int value) {
        if (selectedRow == -1 || model.isFixed(selectedRow, selectedCol)) return;
        model.set(selectedRow, selectedCol, value);
        view.refreshBoard();
    }

    public void removeNumber() {
        if (selectedRow == -1 || model.isFixed(selectedRow, selectedCol)) return;
        model.set(selectedRow, selectedCol, 0);
        view.refreshBoard();
    }

    public void finishGame() {
        if (!model.isComplete() || model.hasErrors()) return;
        javax.swing.JOptionPane.showMessageDialog(view,
                "Parabéns! Sudoku resolvido 🎉");
        startNewGame();
        view.refreshBoard();
    }
}
