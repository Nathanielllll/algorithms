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
        if (board == null || board.length < 1 || board[0].length < 1 ||
                board.length * board[0].length < word.length()) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;

        boolean[][] used = new boolean[rows][cols];
        int length = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backTracking(board, rows, cols, i, j, used, word, length)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean backTracking(char[][] board, int rows, int cols, int row, int col,
                                boolean[][] used, String word, int length) {

        if (length == word.length()) {
            return true;
        }
        boolean result = false;
        if (row >= 0 && row < rows && col >= 0 && col < cols
                && !used[row][col] && word.charAt(length) == board[row][col]) {
            used[row][col] = true;
            length++;
            result = backTracking(board, rows, cols, row + 1, col, used, word, length) ||
                    backTracking(board, rows, cols, row - 1, col, used, word, length) ||
                    backTracking(board, rows, cols, row, col + 1, used, word, length) ||
                    backTracking(board, rows, cols, row, col - 1, used, word, length);
            length--;
            used[row][col] = false;
        }
        return result;
    }

    public static void main(String[] args) {
        Test_79 solution = new Test_79();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        String word_1 = "SEE";
        String word_2 = "ABCB";

        System.out.println(solution.exist(board, word));
        System.out.println(solution.exist(board, word_1));
        System.out.println(solution.exist(board, word_2));
    }

}
