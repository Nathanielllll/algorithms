package backtracking;

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
public class Coding_51 {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new LinkedList<>();
        //都没有结果？？
        if (n <= 0) {
            return result;
        }

        //用stack来存储，有效的不攻击的皇后的：每一列的行号
        Stack<Integer> positions = new Stack<>();
        solveNQueens(result, n, positions);

        return result;
    }

    public static void solveNQueens(List<List<String>> result, int n, Stack<Integer> positions) {
        if (positions.size() == n) {
            addResult(result, n, positions);
            return;
        }

        //那一列的n个位置都需要尝试
        for (int i = 0; i < n; i++) {
            if (noAttack(i, positions)) {
                positions.push(i);
                solveNQueens(result, n, positions);
                positions.pop();
            }
        }
    }

    //确定不相互攻击的函数
    private static boolean noAttack(int n, Stack<Integer> positions) {
        for (int i = 0; i < positions.size(); i++) {
            //行：positions.get(i)    列：i
            //行：n                   列：position.size()
            if (positions.get(i) == n) {
                return false;
            }
            if (Math.abs(positions.get(i) - n) == Math.abs(i - positions.size())) {
                return false;
            }
        }
        return true;
    }

    //返回题目所需要的格式
    private static void addResult(List<List<String>> result, int n, Stack<Integer> positions) {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < positions.size(); i++) {
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

    public static void main(String[] args) {

        System.out.println(solveNQueens(4));
    }
}
