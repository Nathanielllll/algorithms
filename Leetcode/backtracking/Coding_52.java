package backtracking;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Coding_52 {

    private static int res;

    public static int totalNQueens(int n) {
        res = 0;
        if (n <= 0) {
            return 0;
        }
        //用stack来存储，有效的不攻击的皇后的：每一列的行号
        Stack<Integer> positions = new Stack<>();
        solveNQueens(n, positions);
        return res;
    }

    public static void solveNQueens(int n, Stack<Integer> positions) {
        if (positions.size() == n) {
            ++res;
            return;
        }

        //那一列的n个位置都需要尝试
        for (int i = 0; i < n; i++) {
            if (noAttack(i, positions)) {
                positions.push(i);
                solveNQueens(n, positions);
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

    public static void main(String[] args) {
        System.out.println(totalNQueens(4));
    }

}
