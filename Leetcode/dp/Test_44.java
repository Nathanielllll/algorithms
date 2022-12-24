package dp;

public class Test_44 {
    /**
     * 给定一个字符串(s) 和一个字符模式(p) ，实现一个支持'?'和'*'的通配符匹配。
     * <p>
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     * <p>
     * 说明:
     * <p>
     * s可能为空，且只包含从a-z的小写字母。
     * p可能为空，且只包含从a-z的小写字母，以及字符?和*。
     * 示例1:
     * <p>
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例2:
     * <p>
     * 输入:
     * s = "aa"
     * p = "*"
     * 输出: true
     * 解释:'*' 可以匹配任意字符串。
     * 示例3:
     * <p>
     * 输入:
     * s = "cb"
     * p = "?a"
     * 输出: false
     * 解释:'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
     * 示例4:
     * <p>
     * 输入:
     * s = "adceb"
     * p = "*a*b"
     * 输出: true
     * 解释:第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
     * 示例5:
     * <p>
     * 输入:
     * s = "acdcb"
     * p = "a*c?b"
     * 输出: false
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/wildcard-matching
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public boolean isMatch(String ss, String pp) {
        int n = ss.length(), m = pp.length();
        // 技巧：往原字符头部插入空格，这样得到 char 数组是从 1 开始，而且可以使得 f[0][0] = true，可以将 true 这个结果滚动下去
        ss = " " + ss;
        pp = " " + pp;
        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();
        // f(i,j) 代表考虑 s 中的 1~i 字符和 p 中的 1~j 字符 是否匹配
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s[i] == p[j] || p[j] == '?') {
                    f[i][j] = i - 1 >= 0 && f[i - 1][j - 1];
                } else if (p[j] == '*') {
                    // 原来需要满足：dp[i][j] = dp[i][j−1]∣∣dp[i−1][j−1]∣∣...∣∣dp[i−k][j−1](i>=k) 表示当匹配为0个，1个，...，k个
                    // 又因为可以代数化，就有：dp[i−1][j]=dp[i−1][j−1]∣∣dp[i−2][j−1]∣∣...∣∣dp[i−k][j−1](i>=k)
                    // 因此：dp[i][j] = dp[i][j−1]||dp[i−1][j]
                    f[i][j] = f[i][j - 1] || (i - 1 >= 0 && f[i - 1][j]);
                }
            }
        }
        return f[n][m];
    }
}
