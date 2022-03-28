public class offer094 {
    public static int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            dp[j] = j;
        }

        for (int j = 0; j < n; j++) {
            if (isPalindrome[0][j]) {
                dp[j] = 0;
                continue;
            }

            for (int i = 1; i <= j; i++) {
                if (isPalindrome[i][j]) {
                    dp[j] = Math.min(dp[j], dp[i - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(minCut("abb"));
    }
}
