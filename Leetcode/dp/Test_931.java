package dp;

/**
 * @author luzhi
 * @date 2021/4/20
 */
public class Test_931 {
    /*
    给你一个 n x n 的 方形 整数数组matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。

下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。



示例 1：

输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
输出：13
解释：下面是两条和最小的下降路径，用加粗标注：
[[2,1,3],      [[2,1,3],
 [6,5,4],       [6,5,4],
 [7,8,9]]       [7,8,9]]
示例 2：

输入：matrix = [[-19,57],[-40,-5]]
输出：-59
解释：下面是一条和最小的下降路径，用加粗标注：
[[-19,57],
 [-40,-5]]
     */
    public static int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            dp[i] = matrix[0][i];
        }

        for (int i = 1; i < n; i++) {
            int[] pre = dp.clone();
            for (int j = 0; j < m; j++) {
                if (j == 0) {
                    dp[j] = Math.min(pre[j], pre[j + 1]) + matrix[i][j];
                } else if (j == m - 1) {
                    dp[j] = Math.min(pre[j], pre[j - 1]) + matrix[i][j];
                } else {
                    dp[j] = Math.min(pre[j], Math.min(pre[j - 1], pre[j + 1])) + matrix[i][j];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }
}
