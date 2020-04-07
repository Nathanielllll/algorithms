package netEase.packet3;

import java.util.Scanner;

/**
 * 小易定义一个数字序列是完美的，当且仅当对于任意，都满足，即每个数字都要大于等于前面所有数字的和。
 * 现在给定数字序列，小易想请你从中找出最长的一段连续子序列，满足它是完美的。
 * 2
 * 5
 * 1 3 9 2 6
 * 5
 * 4 2 9 16 7
 * <p>
 * 3
 * 3
 */
public class Test_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            int len = scanner.nextInt();
            long[] data = new long[len];
            for (int j = 0; j < len; j++) {
                data[j] = scanner.nextLong();
            }
            System.out.println(longest(data, len));
        }

    }

    public static int longest(long[] data, int len) {
        long sum = data[0];
        int maxLen = 0;
        int left = 0;
        int right = 1;
        while (right < len) {
            if (data[right] >= sum) {
                int curLen = right - left + 1;
                maxLen = Math.max(maxLen, curLen);
                sum += data[right++];
            } else {
                sum = data[right];
                left = right++;
            }
        }
        return maxLen;
    }

}
