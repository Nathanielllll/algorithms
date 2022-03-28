package dp.integerBreak;

/**
 * 给定一个正整数n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36。
 * 说明: 你可以假设n不小于 2 且不大于 58。
 *
 * 2 1*1=1;
 * 3 1*2=2;
 *
 * 公式为：f(n)=max(f(i)*f(n-i))
 *
 *
 * 和LeetCode-14当中的剪绳子一样！！
 */
public class Test_343 {
    public int integerBreak(int length) {
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }
        //在length >= 4的情况下，这几个其实不可分割的（因为最大）
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int n = 4; n <= length; n++) {
            for (int i = 1; i <= n / 2; i++) {
                //i <= n/2是因为实际上是对称的
                //此时需要用：公式为f(n)=max(f(i)*f(n-i)) 其中0<i<n
                dp[n] = Math.max(dp[n], dp[i] * dp[n - i]);
            }
        }
        return dp[length];
    }
}
