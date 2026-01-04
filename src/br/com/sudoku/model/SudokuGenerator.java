package br.com.sudoku.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class SudokuGenerator {

    private static final Random random = new Random();

    public static int[][] generateSolvedBoard() {
        int[][] board = new int[9][9];
        fillBoard(board);
        return board;
    }

    private static boolean fillBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    List<Integer> numbers = shuffledNumbers();
                    for (int num : numbers) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (fillBoard(board)) return true;
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static List<Integer> shuffledNumbers() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) nums.add(i);
        Collections.shuffle(nums);
        return nums;
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }

        int br = row / 3 * 3;
        int bc = col / 3 * 3;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[br + i][bc + j] == num)
                    return false;

        return true;
    }

    public static int[][] generatePuzzle(int holes) {
        int[][] solved = generateSolvedBoard();
        int[][] puzzle = copy(solved);

        while (holes > 0) {
            int r = random.nextInt(9);
            int c = random.nextInt(9);
            if (puzzle[r][c] != 0) {
                puzzle[r][c] = 0;
                holes--;
            }
        }

        return puzzle;
    }

    private static int[][] copy(int[][] src) {
        int[][] dst = new int[9][9];
        for (int i = 0; i < 9; i++)
            System.arraycopy(src[i], 0, dst[i], 0, 9);
        return dst;
    }
}
