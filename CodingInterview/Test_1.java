import java.util.*;

public class Test_1 {
    /*
        表示n个是骰子的和是s
        F(n, s) = F(n-1, s-6) + F(n-1, s-5) + F(n-1, s-4) + F(n-1, s-3) + F(n-1, s-2) + F(n-1, s-1)
     */
    public double[] twoSum(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        for (int j = 1; j <= 6; j++) {
            dp[1][j] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {// sum
                for (int k = 1; k <= 6; k++) {
                    if (j - k >= i - 1) {// 必然有s>=n
                        dp[i][j] += dp[i - 1][j - k];
                    }
                }
            }
        }
        double total = Math.pow(6, n);
        double[] ans = new double[5 * n + 1];
        for (int i = n; i <= 6 * n; i++) {
            ans[i - n] = dp[n][i] / total;
        }
        return ans;
    }
}
