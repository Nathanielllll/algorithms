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
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, i, j, 0, word, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int row, int col, int step, String word, boolean[][] visited) {
        if (step == word.length()) {
            return true;
        }

        boolean result = false;
        if (row >= 0 && row < board.length && col >= 0 && col < board[0].length
                && !visited[row][col] && word.charAt(step) == board[row][col]) {
            visited[row][col] = true;
            step++;
            for (int k = 0; k < 4; k++) {
                int nextRow = dx[k] + row;
                int nextCol = dy[k] + col;
                result = result || dfs(board, nextRow, nextCol, step, word, visited);
            }
            step--;
            visited[row][col] = false;
        }
        return result;
    }

}
