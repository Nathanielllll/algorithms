package dp.integerBreak;

/**
 * 给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class Test_279 {
    //其实和01bag当中的硬币是一样的道理？？

    //dp表示你需要让组成和的完全平方数的个数最少
    //j的平方是j*j, dp[i] = min(dp[i], dp[i-j*j] + 1)
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];

        //每个值 让组成和的完全平方数的个数最大为i
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i && i- j*j >= 0; j++) {//j<i? / j<=i?
                // +1表示必然有dp[j*j]==1
                dp[i] = Math.min(dp[i], dp[i- j*j] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }
}
