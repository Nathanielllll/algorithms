package backTracing;

/**
 * 矩阵中的路径
 * <p>
 * 1.结束的条件 2.进入某个点的条件
 */
public class Code_12 {

    public boolean exist(char[][] board, String word) {
        if (board == null) {
            return false;
        }
        if (word == null) {
            return true;
        }

        int curLength = 0;
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] isUsed = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (subProcess(board, i, j, word, curLength, isUsed)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean subProcess(char[][] board, int row, int col, String word, int curLength, boolean[][] isUsed) {
        if (curLength == word.length()) {
            return true;
        }

        boolean hasPath = false;
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length
                && !isUsed[row][col] && curLength < word.length() && board[row][col] == word.charAt(curLength)) {
            isUsed[row][col] = true;
            // curLength++;
            hasPath = subProcess(board, row + 1, col, word, curLength + 1, isUsed) ||
                    subProcess(board, row - 1, col, word, curLength + 1, isUsed) ||
                    subProcess(board, row, col + 1, word, curLength + 1, isUsed) ||
                    subProcess(board, row, col - 1, word, curLength + 1, isUsed);
            // curLength--;
            isUsed[row][col] = false;
        }
        return hasPath;
    }
}
