package dp;

/**
 * @author luzhi
 * @date 2021/4/26
 */
public class Test_1289 {
    /*
    给你一个整数方阵arr，定义「非零偏移下降路径」为：从arr 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。

请你返回非零偏移下降路径数字和的最小值。

示例 1：

输入：arr = [[1,2,3],[4,5,6],[7,8,9]]
输出：13
解释：
所有非零偏移下降路径包括：
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
下降路径中数字和最小的是[1,5,7] ，所以答案是13 。


我们可以使用动态规划来解决这个问题。

我们用 f[i][j] 表示从数组 arr 的前 i 行分别选择一个数字，并且第 i 行选择的数字为 arr[i][j] 时，可以得到的路径数字和的最小值。f[i][j] 可以从第 i - 1 行除了 f[i - 1][j] 之外的任意状态转移而来，这样我们可以写出如下的状态转移方程：


f[i][j] = min(f[i - 1][j']) + arr[i][j]    其中 j != j'
f[0][j] = arr[0][j]
这个动态规划的时间复杂度为 O(N^3)O(N
3
 )：我们需要使用三重循环分别枚举 i，j 和 j0。若使用 C++ 语言实现该算法，则可以恰好在规定时间内通过所有测试数据，但对于 Python 语言则无法通过。因此我们必须对该算法进行优化。

我们注意到，状态转移方程中的 min(f[i - 1][j']) 在大多数情况下的值都是相同的。不妨记 f[i - 1][jmin] 是第 i - 1 行所有状态中的最小值，可以发现，在状态转移方程中：

如果 j != jmin，那么 f[i][j] 一定会从 f[i - 1][jmin] 转移而来，因为此时 min(f[i - 1][j']) 这一项可以取到最小值；

如果 j == jmin，那么 f[i][j] 不能从 f[i - 1][jmin] 转移而来，那么我们需要选择第 i - 1 行所有状态中的次小值，使得 min(f[i - 1][j']) 这一项取到最小值。

因此我们可以修改状态转移方程：


f[i][j] = f[i - 1][jmin[i - 1]] + arr[i][j]    其中 j != jmin[i - 1]
f[i][j] = f[i - 1][jnext[i - 1]] + arr[i][j]   其中 j == jmin[i - 1]
f[0][j] = arr[0][j]
     */
    public static int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int first_sum = 0, first_pos = -1, second_sum = 0;
        for (int i = 0; i < n; ++i) {
            int fs = Integer.MAX_VALUE, fp = -1, ss = Integer.MAX_VALUE;
            for (int j = 0; j < n; ++j) {
                int cur_sum = (first_pos != j ? first_sum : second_sum) + matrix[i][j];
                if (cur_sum < fs) {
                    ss = fs;
                    fs = cur_sum;
                    fp = j;
                }
                // fs <= cur_sum < ss
                else if (cur_sum < ss) {
                    ss = cur_sum;
                }
            }
            first_sum = fs;
            first_pos = fp;
            second_sum = ss;
        }
        return first_sum;
    }
}
