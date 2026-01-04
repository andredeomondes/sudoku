package br.com.sudoku.model;

public class SudokuBoard {

    private int[][] board;
    private boolean[][] fixed;

    public SudokuBoard() {
        board = new int[9][9];
        fixed = new boolean[9][9];
    }

    public void setFixed(int row, int col, int value) {
        board[row][col] = value;
        fixed[row][col] = true;
    }

    public boolean isFixed(int row, int col) {
        return fixed[row][col];
    }

    public int get(int row, int col) {
        return board[row][col];
    }

    public void set(int row, int col, int value) {
        if (!fixed[row][col]) {
            board[row][col] = value;
        }
    }

    public boolean isEmpty(int row, int col) {
        return board[row][col] == 0;
    }

    public void clearUserNumbers() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!fixed[i][j]) {
                    board[i][j] = 0;
                }
            }
        }
    }
}