package backtracking;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给出集合[1,2,3,…,n]，其所有元素共有n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定n 和k，返回第k个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n的范围是 [1, 9]。
 * 给定 k的范围是[1, n!]。
 * 示例1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class Test_60_ATTENTION {


    /**
     * 记录数字是否使用过
     */
    private boolean[] used;

    /**
     * 阶乘数组
     */
    private int[] factorial;

    private int n;
    private int k;

    public String getPermutation(int n, int k) {
        this.n = n;
        this.k = k;
        calculateFactorial(n);

        // 查找全排列需要的布尔数组
        used = new boolean[n + 1];
        Arrays.fill(used, false);

        StringBuilder path = new StringBuilder();
        dfs(0, path);
        return path.toString();
    }


    /**
     * @param index 在这一步之前已经选择了几个数字，其值恰好等于这一步需要确定的下标位置
     * @param path
     */
    private void dfs(int index, StringBuilder path) {
        if (index == n) {
            return;
        }

        // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        int cnt = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (used[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.append(i);
            used[i] = true;
            dfs(index + 1, path);
            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子结点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    /**
     * 计算阶乘数组
     *
     * @param n
     */
    private void calculateFactorial(int n) {
        factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

}
