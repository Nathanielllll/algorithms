package zhaohang.March_29;

import java.util.Scanner;

/*
2
0 2 1 3

1

求swap的次数
 */
public class Test_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();

        int[] nums = new int[total * 2];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = scanner.nextInt();
        }
//        int[] nums = {0,2,1,3};
        System.out.println(min(nums));


    }

    public static int min(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i += 2) {
            int x = nums[i];
            if (nums[i+1] == (x ^ 1)) {
                continue;
            }
            result++;

            for (int j = i+1; j < nums.length; ++j) {
                if (nums[j] == (x ^ 1)) {
                    nums[j] = nums[i + 1];
                    nums[i + 1] = x^1;
                    break;
                }

            }

        }
        return result;
    }

}
