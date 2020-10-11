package backtracking;

public class Test_97 {
    /*
    给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。

    示例 1：

    输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    输出：true
    示例 2：

    输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    输出：false
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        if(s1 == null) s1 = "";
        if(s2 == null) s2 = "";
        if(s3 == null) s3 = "";
        Boolean[][] memo = new Boolean[s1.length()+1][s2.length()+1];

        return backTracking(s1, s2, s3, 0, 0, 0, memo);
    }

    private boolean backTracking(String s1, String s2, String s3, int i, int j, int k, Boolean[][] memo) {
        if(memo[i][j]!=null) return memo[i][j];

        if(i == s1.length() && j == s2.length() && k == s3.length())
            return true;

        if (k >= s3.length()) {
            return memo[i][j] = false;
        }

        if(i<s1.length() && s1.charAt(i) == s3.charAt(k)
                && backTracking(s1, s2, s3, i+1, j, k+1, memo)){
            return true;
        }
        if(j<s2.length() && s2.charAt(j) == s3.charAt(k)
                && backTracking(s1, s2, s3, i, j+1, k+1, memo)){
            return true;
        }
        return memo[i][j] = false;
    }

    private boolean helper(String s1, String s2, String s3){
        if(s1 == null) s1 = "";
        if(s2 == null) s2 = "";
        if(s3 == null) s3 = "";

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = dp[i-1][j];
                }
                if (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] |= dp[i][j-1];
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
