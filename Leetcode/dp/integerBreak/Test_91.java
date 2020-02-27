package dp.integerBreak;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
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
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
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
    public int numDecodings(String s) {

        int len = s.length();
        int[] dp = new int[len + 1];

        dp[len] = 1;
        if (s.charAt(len - 1) != '0') {
            dp[len - 1] = 1;
        }

        for (int i = len - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                //表面是continue,实际上dp[i]==0
                continue;
            }

            int ans1 = dp[i + 1];//dp[i+1]
            int ans2 = 0;//dp[i+2]

            int num1 = s.charAt(i) - '0';
            int num2 = s.charAt(i + 1) - '0';
            int value = num1 * 10 + num2;
            //实际上，value [10,26]，因为上面的条件为s.charAt(i) == '0'的时候，直接有dp[i] == 0
            if (value <= 26) {
                ans2 = dp[i + 2];
            }
            dp[i] = ans1 + ans2;

        }
        return dp[0];
    }
}
