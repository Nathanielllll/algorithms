package baidu.spring;

import java.util.Scanner;

/**
 * @author luzhi
 * @date 2021/4/28
 */
public class Test_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String t = scanner.next();
        int k = scanner.nextInt();

        process(s, t, k);
    }

    private static void process(String s, String t, int k) {
        int length = s.length();
        long[][] dp = new long[k][length];
        long MOD = 1000000007;
        dp[0][0] = 0;
        for (int j = 1; j < length; j++) {
            dp[0][j] = 1;
        }

        if (k > 1) {
            for (int i = 1; i < k; i++) {
                for (int j = 0; j < length; j++) {
                    for (int l = 0; l < length; l++) {
                        if (j != l && dp[i - 1][l] != 0) {
                            dp[i][j] += dp[i - 1][l];
                            dp[i][j] = dp[i][j] % MOD;
                        }
                    }
                }
            }
        }

        for (int j = 0; j < length; j++) {
            if ((s.substring(j, length) + s.substring(0, j)).equals(t)) {
                System.out.println(dp[k - 1][j]);
                return;
            }
        }
        System.out.println(0);
    }
}
