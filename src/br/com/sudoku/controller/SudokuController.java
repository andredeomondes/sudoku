package br.com.sudoku.controller;

import br.com.sudoku.model.SudokuBoard;
import br.com.sudoku.view.SudokuFrame;

import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class SudokuController {

    private SudokuBoard model;
    private SudokuFrame view;

    private int selectedRow = -1;
    private int selectedCol = -1;

    private Set<Integer>[][] notes = new HashSet[9][9];

    public SudokuController(SudokuFrame view, String[] args) {
        this.view = view;
        this.model = new SudokuBoard();
        initNotes();
        loadFixedNumbers(args);
    }

    private void initNotes() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                notes[i][j] = new HashSet<>();
    }

    private void loadFixedNumbers(String[] args) {
        for (String arg : args) {
            String[] parts = arg.split(",");
            model.setFixed(
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(parts[2])
            );
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

    // ================= AÇÕES =================

    public void insertNumber() {
        if (selectedRow == -1 || model.isFixed(selectedRow, selectedCol)) return;

        String input = JOptionPane.showInputDialog("Número (1-9):");
        if (input == null) return;

        int value = Integer.parseInt(input);
        model.set(selectedRow, selectedCol, value);
        notes[selectedRow][selectedCol].clear();
        view.updateCell(selectedRow, selectedCol, value);
    }

    public void removeNumber() {
        if (selectedRow == -1 || model.isFixed(selectedRow, selectedCol)) return;

        model.set(selectedRow, selectedCol, 0);
        notes[selectedRow][selectedCol].clear();
        view.updateCell(selectedRow, selectedCol, 0);
    }

    public void note() {
        if (selectedRow == -1 || model.isFixed(selectedRow, selectedCol)) return;

        String input = JOptionPane.showInputDialog("Rascunho (1-9):");
        if (input == null) return;

        int n = Integer.parseInt(input);
        if (!notes[selectedRow][selectedCol].add(n))
            notes[selectedRow][selectedCol].remove(n);

        view.updateNotes(selectedRow, selectedCol, notes[selectedRow][selectedCol]);
    }

    public void showStatus() {
        String status;

        if (!model.isComplete()) {
            status = "Jogo incompleto";
        } else {
            status = "Jogo completo";
        }

        status += model.hasErrors() ? " (com erros)" : " (sem erros)";

        JOptionPane.showMessageDialog(view, status);
    }
}
