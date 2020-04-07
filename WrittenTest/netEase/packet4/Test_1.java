package netEase.packet4;

import java.util.*;

/**
 * 小易给你一个包含n个数字的数组。你可以对这个数组执行任意次以下交换操作：
 * 对于数组中的两个下标i,j(1<=i,j<=n)，如果为a_i+a_j奇数，就可以交换a_i和a_j。
 *
 * 现在允许你使用操作次数不限，小易希望你能求出在所有能通过若干次操作可以得到的数组中，字典序最小的一个是什么。
 */
public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer n = scanner.nextInt();
        Integer nums[] = new Integer[n];
        int evenCount = 0;
        int oddCount = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
            if (nums[i] % 2 == 0)
                evenCount++;
            else
                oddCount++;
        }
        if (evenCount != 0 && oddCount != 0) {
            Arrays.sort(nums);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
