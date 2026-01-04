package br.com.sudoku.model;

public class SudokuBoard {

    private int[][] board = new int[9][9];
    private boolean[][] fixed = new boolean[9][9];

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

    public boolean isComplete() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] == 0)
                    return false;
        return true;
    }

    public boolean hasErrors() {
        for (int i = 0; i < 9; i++) {
            boolean[] rowCheck = new boolean[10];
            boolean[] colCheck = new boolean[10];

            for (int j = 0; j < 9; j++) {
                int r = board[i][j];
                int c = board[j][i];

                if (r != 0) {
                    if (rowCheck[r]) return true;
                    rowCheck[r] = true;
                }

                if (c != 0) {
                    if (colCheck[c]) return true;
                    colCheck[c] = true;
                }
            }
        }

        for (int br = 0; br < 9; br += 3) {
            for (int bc = 0; bc < 9; bc += 3) {
                boolean[] check = new boolean[10];

                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        int v = board[br + i][bc + j];
                        if (v != 0) {
                            if (check[v]) return true;
                            check[v] = true;
                        }
                    }
            }
        }

        return false;
    }

    public void clearUserNumbers() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (!fixed[i][j])
                    board[i][j] = 0;
    }
}
