package dp.editDistance;

/**
 * 给定两个单词word1和word2，找到使得word1和word2相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "sea", "eat" 输出: 2 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea" 说明:
 * <p>
 * 给定单词的长度不超过500。 给定单词中的字符只含有小写字母。
 */
public class Test_583 {

  public int minDistance1(String word1, String word2) {
    int len1 = word1.length();
    int len2 = word2.length();

    int[][] dp = new int[len1 + 1][len2 + 1];
    for (int i = 1; i <= len1; i++) {
      dp[i][0] = i;
    }
    for (int j = 0; j <= len2; j++) {
      dp[0][j] = j;
    }
    for (int i = 1; i <= len1; i++) {
      for (int j = 1; j <= len2; j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.min(dp[i - 1][j - 1] + 2, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
        }
      }
    }
    return dp[len1][len2];
  }

  //可以转换为求两个字符串的最长公共子序列问题。
  public int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    int[][] dp = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        char c1 = word1.charAt(i - 1);
        char c2 = word2.charAt(j - 1);
        if (c1 == c2) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return m + n - 2 * dp[m][n];
  }

  public int minDistance2(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();

    int[][] memo = new int[m][n];
    return minDistanceDfs(m - 1, n - 1, word1, word2, memo);
  }

  private int minDistanceDfs(int i, int j, String word1, String word2, int[][] memo) {
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
      memo[i][j] = Math.min(minDistanceDfs(i - 1, j - 1, word1, word2, memo) + 2,
          Math.min(minDistanceDfs(i, j - 1, word1, word2, memo) + 1,
              minDistanceDfs(i - 1, j, word1, word2, memo) + 1));
    }
    return memo[i][j];
  }
}
