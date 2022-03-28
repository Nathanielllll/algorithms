package backtracking;

import java.util.*;

/**
 * n皇后问题研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的n皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 */
public class Test_51 {
    List<List<String>> result;
    public List<List<String>> solveNQueens(int n) {
        result = new ArrayList<>();

        String[][] record = new String[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(record[i], ".");
        }

        backTracking(record, 0, n);
        return result;
    }

    private void backTracking(String[][] record, int row, int n) {
        if (row == n) {
            addResult(record, n);
            return;
        }

        // 对每一列进行遍历，是否满足
        for (int col = 0; col < n; col++) {
            if (!isValid(record, row, col, n)) {
                continue;
            }
            record[row][col] = "Q";
            backTracking(record, row + 1, n);
            record[row][col] = ".";
        }
    }

    private boolean isValid(String[][] record, int row, int col, int n) {
        // 1. 正上方
        for (int i = 0; i < row; i++) {
            if (record[i][col].equals("Q")) {
                return false;
            }
        }

        // 2. 右上方
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (record[i][j].equals("Q")) {
                return false;
            }
        }

        // 3. 左上方
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (record[i][j].equals("Q")) {
                return false;
            }
        }

        return true;
    }

    private void addResult(String[][] record, int n) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(record[i][j]);
            }
            list.add(sb.toString());
        }
        result.add(list);
    }
}
