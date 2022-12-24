package dp;

//https://leetcode-cn.com/problems/regular-expression-matching/solution/dong-tai-gui-hua-zen-yao-cong-0kai-shi-si-kao-da-b/

public class Test_10_ATTENTION {
    public boolean isMatch(String s_string, String p_string) {
        int m = s_string.length(), n = p_string.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        //"" 和p的匹配关系初始化。a*a*a*a*a*这种能够匹配空串，其他的是都是false。
        //  奇数位不管什么字符都是false；偶数位为*时，因为*必然只能取0次，因此有: dp[0][i] = dp[0][i - 2]
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
                    if (s[i - 1] == p[j - 2] || p[j - 2] == '.') {
                        // 假设为：##b , ###b*
                        dp[i][j] = dp[i - 1][j] // 多个字符匹配的情况，即：## 和 ###b * 能否匹配，如果能，则 ##b 也必然能和 ###b * 匹配
                                || dp[i][j - 1] // 单个字符匹配的情况，即：##b 和 ###b 能否匹配，如果能，则 ##b 也必然能和 ###b * 匹配
                                || dp[i][j - 2];// 没有字符匹配的情况，即：##b 和 ### 能否匹配，如果能，则 ##b 也必然能和 ###b * 匹配
                    } else {
                        // 假设为：##b , ###a*
                        dp[i][j] = dp[i][j - 2];// 没有字符匹配的情况，即*重复了0次
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
