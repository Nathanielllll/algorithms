package tencent;

import java.util.Scanner;

/**
 * 由于业绩优秀，公司给小Q放了 n 天的假，
 * 身为工作狂的小Q打算在在假期中工作、锻炼或者休息。
 * 他有个奇怪的习惯：不会连续两天工作或锻炼。只有当公司营业时，小Q才能去工作，
 * 只有当健身房营业时，小Q才能去健身，小Q一天只能干一件事。
 * 给出假期中公司，健身房的营业情况，求小Q最少需要休息几天。
 */
public class Test_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int[][] arr = new int[2][n];
        //工作
        for (int i = 0; i < n; i++) {
            arr[0][i] = scanner.nextInt();
        }
        //健身
        for (int i = 0; i < n; i++) {
            arr[1][i] = scanner.nextInt();
        }

        //动态规划，dp中记录3中状态下有事可做的最多天数
        int[][] dp = new int[3][n];
        if (arr[0][0] == 1) {
            dp[1][0] = 1;
        }
        if (arr[1][0] == 1) {
            dp[2][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            //休息的话，前一天干什么都可以
            dp[0][i] = Math.max(dp[0][i-1], Math.max(dp[1][i-1], dp[2][i-1]));
            //工作的话，要今天的arr[0][i]==1，而且昨天不能工作
            if (arr[0][i] == 1) {
                dp[1][i] = Math.max(dp[0][i-1], dp[2][i-1]) + 1;
            }
            //健身的话，要今天的arr[0][i]==1，而且昨天不能健身
            if (arr[1][i] == 1) {
                dp[2][i] = Math.max(dp[0][i-1], dp[1][i-1]) + 1;
            }
        }
        int max = Math.max(dp[0][n-1], Math.max(dp[1][n-1], dp[2][n-1]));

        System.out.println(n - max);
    }

}
