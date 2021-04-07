package backtracking;

public class Test_97_ATTENTION {
    /*
    给定三个字符串s1, s2, s3, 验证s3是否是由s1和s2 交错组成的。

    示例 1：

    输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    输出：true
    示例2：

    输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    输出：false
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null) s1 = "";
        if (s2 == null) s2 = "";
        if (s3 == null) s3 = "";
        Boolean[][] memo = new Boolean[s1.length() + 1][s2.length() + 1];

        return backTracking(s1, s2, s3, 0, 0, 0, memo);
    }

    private boolean backTracking(String s1, String s2, String s3, int i, int j, int k, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];

        if (i == s1.length() && j == s2.length() && k == s3.length())
            return true;

        if (k >= s3.length()) {
            return memo[i][j] = false;
        }

        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)
                && backTracking(s1, s2, s3, i + 1, j, k + 1, memo)) {
            return true;
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(k)
                && backTracking(s1, s2, s3, i, j + 1, k + 1, memo)) {
            return true;
        }
        return memo[i][j] = false;
    }
}
