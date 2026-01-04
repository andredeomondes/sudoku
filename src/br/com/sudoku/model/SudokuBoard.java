package br.com.sudoku.model;

public class SudokuBoard {

    private int[][] board = new int[9][9];
    private boolean[][] fixed = new boolean[9][9];

    public void load(int[][] puzzle) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                board[i][j] = puzzle[i][j];
                fixed[i][j] = puzzle[i][j] != 0;
            }
    }

    public int get(int r, int c) {
        return board[r][c];
    }

    public void set(int r, int c, int v) {
        if (!fixed[r][c]) board[r][c] = v;
    }

    public boolean isFixed(int r, int c) {
        return fixed[r][c];
    }

    public void clearUserNumbers() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (!fixed[i][j]) board[i][j] = 0;
    }

    public boolean isComplete() {
        for (int[] row : board)
            for (int v : row)
                if (v == 0) return false;
        return true;
    }

    public boolean hasErrors() {
        boolean[] check = new boolean[10];

        for (int i = 0; i < 9; i++) {
            java.util.Arrays.fill(check, false);
            for (int j = 0; j < 9; j++) {
                int v = board[i][j];
                if (v != 0 && check[v]) return true;
                if (v != 0) check[v] = true;
            }

            java.util.Arrays.fill(check, false);
            for (int j = 0; j < 9; j++) {
                int v = board[j][i];
                if (v != 0 && check[v]) return true;
                if (v != 0) check[v] = true;
            }
        }

        for (int br = 0; br < 9; br += 3)
            for (int bc = 0; bc < 9; bc += 3) {
                java.util.Arrays.fill(check, false);
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        int v = board[br + i][bc + j];
                        if (v != 0 && check[v]) return true;
                        if (v != 0) check[v] = true;
                    }
            }
        return false;
    }
}
