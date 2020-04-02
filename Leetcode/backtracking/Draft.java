package backtracking;

import java.util.*;

public class Draft {

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length < 1 || board[0].length < 1 ||
                board.length * board[0].length < word.length()) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;
        boolean[][] used = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (subProcess(board, word, 0, used, i, j, rows, cols)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean subProcess(char[][] board, String word, int length, boolean[][] used,
                              int row, int col, int rows, int cols){
        if(length == word.length()){
            return true;
        }

        boolean result = false;
        if(row >= 0 && row < rows && col >= 0 && col < cols
                && board[row][col] == word.charAt(length) && !used[row][col]){
            length++;
            used[row][col] = true;
            result =
                    subProcess(board, word, length, used, row + 1, col, rows, cols) ||
                    subProcess(board, word, length, used, row - 1, col, rows, cols) ||
                    subProcess(board, word, length, used, row, col + 1, rows, cols) ||
                    subProcess(board, word, length, used, row, col - 1, rows, cols);
            length--;
            used[row][col] = false;
        }
        return result;

    }





}
