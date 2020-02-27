package dp;

import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class Test_120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();

        int[] dp = new int[rows + 1];
        for (int row = rows - 1; row >= 0; row--) {
            int cols = triangle.get(row).size();
            for (int i = 0; i < cols; i++) {
                int value = triangle.get(row).get(i);
                dp[i] = Math.min(dp[i], dp[i + 1]) + value;
            }
        }
        return dp[0];
    }
}
