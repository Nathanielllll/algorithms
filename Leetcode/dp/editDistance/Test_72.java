package dp.editDistance;

/**
 * 给定两个单词word1 和word2，计算出将word1转换成word2 所使用的最少操作数。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例1:
 *
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例2:
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
 *                 dp(i, j - 1) + 1,    # 插入
 *                 dp(i - 1, j) + 1,    # 删除
 *                 dp(i - 1, j - 1)) + 1 # 替换
 *
 *
 *
 * 注意！！dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数。因此它们都是已经匹配好的。
 *
 * 对“dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。”的补充理解：
 * 以 word1 为 "horse"，word2 为 "ros"，且 dp[5][3] 为例，即要将 word1的前 5 个字符转换为 word2的前 3 个字符，也就是将 horse 转换为 ros，因此有：
 * (1) dp[i-1][j-1]，即先将 word1 的前 4 个字符 hors 转换为 word2 的前 2 个字符 ro，然后将第五个字符 word1[4]（因为下标基数以 0 开始） 由 e 替换为 s（即替换为 word2 的第三个字符，word2[2]）
 * (2) dp[i][j-1]，即先将 word1 的前 5 个字符 horse 转换为 word2 的前 2 个字符 ro，然后在末尾补充一个 s，即插入操作
 * (3) dp[i-1][j]，即先将 word1 的前 4 个字符 hors 转换为 word2 的前 3 个字符 ros，然后删除 word1 的第 5 个字符
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


    public static int minDistance1(String word1, String word2) {
        int size1 = word1.length();
        int size2 = word2.length();
        int[][] memo = new int[size1][size2];

        return minDistanceDfs(size1 - 1, size2 - 1, word1, word2, memo);
    }

    private static int minDistanceDfs(int i, int j, String word1, String word2, int[][] memo) {
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        char ch1 = word1.charAt(i);
        char ch2 = word2.charAt(j);

        if (ch1 == ch2) {
            memo[i][j] = minDistanceDfs(i - 1, j - 1, word1, word2, memo);
        } else {
            memo[i][j] = Math.min(minDistanceDfs(i - 1, j, word1, word2, memo),
                Math.min(minDistanceDfs(i, j - 1, word1, word2, memo),
                    minDistanceDfs(i - 1, j - 1, word1, word2, memo))) + 1;
        }
        return memo[i][j];
    }
}
