package baidu.spring;

import java.util.Scanner;

/**
 * @author luzhi
 * @date 2021/4/28
 */

public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int length = scanner.nextInt();
        String num = scanner.next();
        System.out.println(process(length, num));
    }

    private static int process(int length, String num) {
        int[] dp = new int[length];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i < length; i++) {
            for (int j = 0; j < i - 1; j++) {
                if (num.charAt(j) == num.charAt(i)) {
                    dp[i] = Math.min(dp[j], dp[i - 1]) + 1;
                    break;
                }
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp[length - 1];
    }


}
