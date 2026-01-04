package br.com.sudoku.controller;

import br.com.sudoku.model.*;
import br.com.sudoku.view.SudokuFrame;

import javax.swing.*;

public class SudokuController {

    private SudokuBoard model = new SudokuBoard();
    private SudokuFrame view;

    private int selRow = -1;
    private int selCol = -1;
    private int correctCount = 0;

    public SudokuController(SudokuFrame view) {
        this.view = view;
    }

    public void startNewGame() {
        int[][] puzzle = SudokuGenerator.generatePuzzle(40);
        model.load(puzzle);
        correctCount = 0;
    }

    public SudokuBoard getModel() {
        return model;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void selectCell(int r, int c) {
        selRow = r;
        selCol = c;
    }

    public void insertNumber(int v) {
        if (selRow == -1 || model.isFixed(selRow, selCol)) return;
        model.set(selRow, selCol, v);
        correctCount++;
        view.refreshBoard();
    }

    public void removeNumber() {
        if (selRow == -1 || model.isFixed(selRow, selCol)) return;
        model.clear(selRow, selCol);
        view.refreshBoard();
    }

    public void toggleNote(int v) {
        if (selRow == -1) return;
        model.toggleNote(selRow, selCol, v);
        view.refreshBoard();
    }
    public void finishGame() {

        if (!model.isComplete()) {
            JOptionPane.showMessageDialog(view,
                    "O jogo ainda não está completo.\nPreencha todos os espaços.");
            return;
        }

        if (model.countErrors() > 0) {
            JOptionPane.showMessageDialog(view,
                    "O jogo contém erros.\nCorrija antes de finalizar.");
            return;
        }

        JOptionPane.showMessageDialog(view,
                "🎉 Parabéns! Você concluiu o Sudoku corretamente!");
        System.exit(0);
    }

}
