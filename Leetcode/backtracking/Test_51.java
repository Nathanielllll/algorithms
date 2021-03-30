package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
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
        if (n <= 0) {
            return result;
        }

        //用stack来存储，有效的不攻击的皇后的：每一列的行号
        Stack<Integer> positions = new Stack<>();
        process(positions, n);
        return result;
    }

    private void process(Stack<Integer> positions, int n) {
        if (positions.size() == n) {
            addResult(n, positions);
        }

        //那一列的全部n行都需要尝试
        for (int i = 0; i < n; i++) {
            if (noAttack(positions, i)) {
                positions.push(i);
                process(positions, n);
                positions.pop();
            }
        }
    }

    private boolean noAttack(Stack<Integer> positions, int curRow) {
        for (int i = 0; i < positions.size(); i++) {
            // 两个位置分别是：(curPos, positions.size())  (positions.get(i), i)
            int row1 = positions.get(i);
            int col1 = i;

            int row2 = curRow;
            int col2 = positions.size();

            // 1. 必然不是在同一列，因此不用判断

            // 2. 判断不是在同一行
            if (row1 == row2) {
                return false;
            }

            // 3. 判断是不是在一条斜线上面
            if (Math.abs(row1 - row2) == Math.abs(col1 - col2)) {
                return false;
            }
        }
        return true;
    }

    private void addResult(int n, Stack<Integer> positions) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int j = 0; j < n; j++) {
                if (j == positions.get(i)) {
                    stringBuffer.append("Q");
                } else {
                    stringBuffer.append(".");
                }
            }
            list.add(stringBuffer.toString());
        }
        result.add(list);
    }
}
