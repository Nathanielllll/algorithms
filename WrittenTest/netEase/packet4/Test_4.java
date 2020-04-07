package netEase.packet4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 小易在维护数据的时候遇到一个需求，具体来说小易有一系列数据，这些数据了构成一个长度为n的数字序列，接下来小易会在这个序列上进行q次操作。
 * 每次操作有一个查询的数字x，小易需要将序列数据中所有大于等于x的数字都减一，并输出在本次操作中有多少个数字被减一了。
 * 小易犯了难，希望你能帮帮他。
 */
public class Test_4 {
    /**
     * 4 3
     * 1 2 3 4
     * 4
     * 3
     * 1
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        Arrays.sort(nums);
        for (int i = 0; i < q; i++) {
            int x = scanner.nextInt();
            int count = 0;
            for (int index = n - 1; index >= 0; index--) {
                //因为每一次只减1，所以可以这样操作的！！
                if (nums[index] >= x) {
                    nums[index]--;
                    count++;
                } else {
                    break;
                }
            }
            System.out.println(count);
        }
    }
}
