package dp;

public class Test_115_ATTENTION {
    /*
    115. 不同的子序列
    给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。

    一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）

    题目数据保证答案符合 32 位带符号整数范围。

    示例 1：

    输入：S = "rabbbit", T = "rabbit"
    输出：3
    解释：

    如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
    (上箭头符号 ^ 表示选取的字母)

    rabbbit
    ^^^^ ^^
    rabbbit
    ^^ ^^^^
    rabbbit
    ^^^ ^^^

    本题相当于只有删除操作，不用考虑替换增加之类的。
    dp[i][j]：以i-1为结尾的s子序列中出现以j-1为结尾的t的个数为dp[i][j]。
    https://leetcode-cn.com/problems/distinct-subsequences/solution/115-bu-tong-de-zi-xu-lie-dong-tai-gui-hu-ko1z/
     */
    public int numDistinct(String s, String t) {
        int s_length = s.length();
        int t_length = t.length();
        int[][] dp = new int[s_length + 1][t_length + 1];
        for (int i = 0; i <= s_length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= s_length; i++) {
            for (int j = 1; j <= t_length; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s_length][t_length];
    }

}
