package dp.integerBreak;

/**
 * 一条包含字母A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12" 输出: 2 解释:它可以解码为 "AB"（1 2）或者 "L"（12）。 示例2:
 * <p>
 * 输入: "226" 输出: 3 解释:它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 比如 232232323232。 对于第一个字母我们有两种划分方式。 2|32232323232 和 23|2232323232
 * <p>
 * 2|92232323232 和 29|2232323232 ans2 = 0!! 所以，如果我们分别知道了上边划分的右半部分 32232323232 的解码方式是 ans1
 * 种，2232323232 的解码方式是 ans2 种， 那么整体 232232323232 的解码方式就是 ans1 + ans2 种。
 */
public class Test_91 {

  public int numDecodings(String s) {
    int n = s.length();
    s = " " + s;
    int[] dp = new int[n + 1];
    // 必须要有一个边界条件，否则后续只能为0
    dp[0] = 1;
    char[] chars = s.toCharArray();
    for (int i = 1; i <= n; i++) {
      // a : 代表「当前位置」单独形成 item
      int a = chars[i] - '0';
      // b : 代表「当前位置」与「前一位置」共同形成 item
      int b = (chars[i - 1] - '0') * 10 + (chars[i] - '0');
      // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
      if (1 <= a && a <= 9) {
        dp[i] += dp[i - 1];
      }
      // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
      if (10 <= b && b <= 26) {
        dp[i] += dp[i - 2];
      }
    }
    return dp[n];
  }
}
