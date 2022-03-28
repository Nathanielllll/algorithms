package dp;

public class Test_516 {
    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(longestPalindromeSubseq(s));
    }

    public static int longestPalindromeSubseq(String s) {
        int length = s.length();
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }

        // s[i]=s[j]时, dp[i][j] = dp[i+1][j-1]+2
        // s[i]!=s[j]时, dp[i][j] = max(dp[i+1][j], dp[i][j-1])
        // 注意边界问题
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = (i + 1 <= j - 1 ? dp[i + 1][j - 1] : 0) + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }

    /**
     * 压缩空间
     *
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq02(String s) {
        int length = s.length();
        int[] dp = new int[length];
        for (int j = 0; j < length; j++) {
            dp[j] = 1;
        }

        for (int i = length - 2; i >= 0; i--) {
            int pre = 0;
            for (int j = i + 1; j < length; j++) {
                int temp = dp[j];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = (i + 1 <= j - 1 ? pre : 0) + 2;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                pre = temp;
            }

        }
        return dp[length - 1];
    }

}
