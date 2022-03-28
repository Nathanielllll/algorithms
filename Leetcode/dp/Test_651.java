package dp;

/**
 * 题目描述：
 *
 * 假设你有一个特殊的键盘，上面只有四个键，它们分别是：
 *
 * A键：在屏幕上显示一个A
 * ctrl-A：选中整个屏幕
 * ctrl-C：将选中的屏幕复制到缓冲区
 * ctrl-V：将缓冲区的内容输出到光标所在的屏幕的位置
 */
public class Test_651 {
    public int maxA(int n){
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            // 这次按A键
            dp[i] = dp[i - 1] + 1;
            // 这次按 ctrl-V 键
            for (int j = 2; j < i; j++) {
                // dp[j - 2]表示ctrl-A 及 ctrl-C 操作
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        return dp[n];
    }
}
