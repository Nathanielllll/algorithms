package dp.integerBreak;

/**
 * 一条包含字母A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释:它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释:它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * 比如 232232323232。
 * 对于第一个字母我们有两种划分方式。
 * 2|32232323232 和 23|2232323232
 *
 * 2|92232323232 和 29|2232323232 ans2 = 0!!
 * 所以，如果我们分别知道了上边划分的右半部分 32232323232 的解码方式是 ans1 种，2232323232 的解码方式是 ans2 种，
 * 那么整体 232232323232 的解码方式就是 ans1 + ans2 种。
 *
 */
public class Test_91 {
    public static int numDecodings(String s) {
        int length = s.length();
        int[] dp = new int[length + 1];
        // base case：初始化条件？
        dp[length] = 1;
        dp[length - 1] = s.charAt(length - 1) == '0' ? 0 : 1;
        for (int i = s.length() - 2; i >= 0; i--) {
            String str1 = s.substring(i, i + 1);
            String str2 = s.substring(i, i + 2);
            if (meetRequirement(str1)) {
                dp[i] += dp[i + 1];
            }
            if (meetRequirement(str2)) {
                dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }

    private static boolean meetRequirement(String s) {
        int result = 0;
        try {
            result = Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }

        if (s.length() == 1) {
            return result >= 1 && result <= 9;
        } else {
            return result >= 10 && result <= 26;
        }
    }

}
