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
        //本题相当于只有删除操作，不用考虑替换增加之类的。
        //dp[i][j]：以i-1为结尾的s子序列中出现以j-1为结尾的t的个数为dp[i][j]。
        int s_length = s.length();
        int t_length = t.length();
        int[][] dp = new int[s_length + 1][t_length + 1];
        // 从递推公式dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; 和 dp[i][j] = dp[i - 1][j]; 中可以看出dp[i][0] 和dp[0][j]是一定要初始化的。
        //每次当初始化的时候，都要回顾一下dp[i][j]的定义，不要凭感觉初始化。
        //dp[i][0]表示什么呢？
        //dp[i][0] 以i-1为结尾的s可以随便删除元素，出现空字符串的个数。
        //那么dp[i][0]一定都是1，因为也就是把以i-1为结尾的s，删除所有元素，出现空字符串的个数就是1。
        //再来看dp[0][j]，dp[0][j]：空字符串s可以随便删除元素，出现以j-1为结尾的字符串t的个数。
        //那么dp[0][j]一定都是0，s如论如何也变成不了t。
        //最后就要看一个特殊位置了，即：dp[0][0] 应该是多少。
        //dp[0][0]应该是1，空字符串s，可以删除0个元素，变成空字符串t。
        for (int i = 0; i <= s_length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= s_length; i++) {
            for (int j = 1; j <= t_length; j++) {
                // 当二者相等时，有两种选择：
                // 1. 一部分是用s[i - 1]来匹配，那么个数为dp[i - 1][j - 1]。
                // 2. 一部分是不用s[i - 1]来匹配，个数为dp[i - 1][j]。
                // 例如： s：bagg 和 t：bag ，s[3] 和 t[2]是相同的，
                // 但是s也可以不用s[3]来匹配，即s[0]s[1]s[2]组成的bag。当然也可以用s[3]来匹配，即：s[0]s[1]s[3]组成的bag。
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
                // 当二者不相等时，只有一种选择：不用s[i - 1]来匹配，个数为dp[i - 1][j]
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s_length][t_length];
    }

}
