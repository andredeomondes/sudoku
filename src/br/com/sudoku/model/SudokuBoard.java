package br.com.sudoku.model;

import java.util.HashSet;
import java.util.Set;

public class SudokuBoard {

    private int[][] board = new int[9][9];
    private boolean[][] fixed = new boolean[9][9];
    private Set<Integer>[][] notes = new HashSet[9][9];

    public SudokuBoard() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                notes[i][j] = new HashSet<>();
    }

    public int countErrors() {
        int errors = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != 0 && !isValid(i, j, board[i][j])) {
                    errors++;
                }
            }
        }
        return errors;
    }

    public int countFilled() {
        int count = 0;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (board[i][j] != 0)
                    count++;
        return count;
    }
    public boolean isValid(int row, int col, int value) {

        // Linha
        for (int c = 0; c < 9; c++) {
            if (c != col && board[row][c] == value) return false;
        }

        // Coluna
        for (int r = 0; r < 9; r++) {
            if (r != row && board[r][col] == value) return false;
        }

        // Bloco 3x3
        int br = (row / 3) * 3;
        int bc = (col / 3) * 3;

        for (int r = br; r < br + 3; r++)
            for (int c = bc; c < bc + 3; c++)
                if ((r != row || c != col) && board[r][c] == value)
                    return false;

        return true;
    }


    public void load(int[][] puzzle) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                board[i][j] = puzzle[i][j];
                fixed[i][j] = puzzle[i][j] != 0;
                notes[i][j].clear();
            }
    }

    public int get(int r, int c) {
        return board[r][c];
    }

    public boolean isFixed(int r, int c) {
        return fixed[r][c];
    }

    public void set(int r, int c, int v) {
        if (!fixed[r][c]) {
            board[r][c] = v;
            notes[r][c].clear();
        }
    }

    public void clear(int r, int c) {
        if (!fixed[r][c]) {
            board[r][c] = 0;
            notes[r][c].clear();
        }
    }

    public void toggleNote(int r, int c, int v) {
        if (!fixed[r][c] && board[r][c] == 0) {
            if (!notes[r][c].add(v))
                notes[r][c].remove(v);
        }
    }

    public Set<Integer> getNotes(int r, int c) {
        return notes[r][c];
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
