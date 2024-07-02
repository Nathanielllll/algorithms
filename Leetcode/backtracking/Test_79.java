package backtracking;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 */
public class Test_79 {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int[][] used = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (existDfs(board, word, used, 0, i, j, m, n)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existDfs(char[][] board, String word, int[][] used, int idx, int i, int j, int m,
        int n) {
        if (idx == word.length()) {
            return true;
        }

        boolean result = false;
        if (i >= 0 && i < m && j >= 0 && j < n && used[i][j] == 0 && board[i][j] == word.charAt(idx)) {
            used[i][j] = 1;
            result = existDfs(board, word, used, idx + 1, i - 1, j, m, n)
                || existDfs(board, word, used, idx + 1, i + 1, j, m, n)
                || existDfs(board, word, used, idx + 1, i, j - 1, m, n)
                || existDfs(board, word, used, idx + 1, i, j + 1, m, n);
            used[i][j] = 0;
        }
        return result;
    }

}
