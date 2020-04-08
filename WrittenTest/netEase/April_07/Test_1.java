package netEase.April_07;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 4
 * 1 3 7 15
 *
 * 2
 *
 * AC 90%
 */
public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        long[] nums = new long[len];
        for (int i = 0; i < len; i++) {
            nums[i] = scanner.nextLong();
        }

        long[] gap = new long[len - 1];
        for (int i = 1; i < len; i++) {
            gap[i - 1] = nums[i] - nums[i-1];
        }

        Arrays.sort(gap);
        //特殊情况
        if (gap[0] == 0 || gap[0] < 0) {
            System.out.println(-1);
            return;
        }

        long yinshu = gap[0];
        for (int i = 1; i < gap.length - 1; i++) {
            if (gap[i] % yinshu != 0) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(yinshu);


    }
}
