package dp.editDistance;

/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 *
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 *
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-mian-shi-ti-xiang-jie-by-labuladong/
 * dp[i][j] : 返回 s1[0..i] 和 s2[0..j] 的最小编辑距离
 *
 *
 * def dp(i, j):
 *         # base case
 *         if i == -1: return j + 1
 *         if j == -1: return i + 1
 *
 *         if s1[i] == s2[j]:
 *             return dp(i - 1, j - 1)  # 啥都不做
 *         else:
 *             return min(
 *                 dp(i, j - 1) + 1,    # 插入 s1的前i个和s2的前j-1个都匹配了，但是s1[i]!=s2[j]，只要s1插入一个s2[j]就可以了
 *                 dp(i - 1, j) + 1,    # 删除
 *                 dp(i - 1, j - 1)) + 1 # 替换
 *
 */
public class Test_72 {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        // 相当于对 word1 执行 i 次删除操作
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        // 相当于对 word1执行 j 次插入操作
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // i==0的时候表示空字符串。因为i是从1开始的，所以实际表示的是word1[i] == word2[j]
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
