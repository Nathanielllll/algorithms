package dp;

import java.util.Arrays;
//https://leetcode-cn.com/problems/regular-expression-matching/solution/dong-tai-gui-hua-zen-yao-cong-0kai-shi-si-kao-da-b/

public class Test_10_ATTENTION {
    public boolean isMatch(String s_string, String p_string) {
        int m = s_string.length(), n = p_string.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        //"" 和p的匹配关系初始化，a*a*a*a*a*这种能够匹配空串，其他的是都是false。
        //  奇数位不管什么字符都是false，偶数位为* 时则: dp[0][i] = dp[0][i - 2]
        for (int j = 2; j <= n; j += 2) {
            if (p_string.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        char[] s = s_string.toCharArray();
        char[] p = p_string.toCharArray();

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s[i - 1] == p[j - 1] || p[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p[j - 1] == '*') {
                    if (s[i - 1] != p[j - 2] && p[j - 2] != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        //dp[i][j-1] 其实是多余的
                        //原因在于检查多个字符匹配的时候也是要落到检查单个字符最后落到检查empty，所以我们只需要实现检查多个字符和empty字符的情况。
                        //dp[i][j] = dp[i][j - 1] || dp[i][j - 2] || dp[i - 1][j];
                        dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        Test_10_ATTENTION test = new Test_10_ATTENTION();
        String s = "aab";
        String p = "c*a*b";
        System.out.println(test.isMatch(s, p));
    }
}
