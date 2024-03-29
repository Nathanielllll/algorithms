//public class offer097 {
//    public int numDistinct(String s, String t) {
//        return s.length() + t.length() - 2 * longestCommonSubsequence(s, t);
//    }
//
//    public static int longestCommonSubsequence(String text1, String text2) {
//        int n1 = text1.length();
//        int n2 = text2.length();
//        int[][] dp = new int[n1 + 1][n2 + 1];
//
//        for (int i = 1; i <= n1; i++) {
//            for (int j = 1; j <= n2; j++) {
//                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1] + 1;
//                } else {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
//                }
//            }
//        }
//        return dp[n1][n2];
//    }
//}
