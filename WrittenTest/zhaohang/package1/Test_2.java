package zhaohang.package1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 2
 * 21 1
 * 12345 3
 *
 * 1
 * 1
 */
public class Test_2 {
    //01背包问题？
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        for (int i = 0; i < total; i++) {
            String string = scanner.next();
            int target = scanner.nextInt();

            int[] nums = new int[string.length()];
            for (int j = 0; j < string.length(); j++) {
                nums[j] = string.charAt(j) - '0';
            }
            System.out.println(subProcess(nums, target));
        }

//        //Test:
//        int[] nums = {1,2,3,4,5};
//        int target = 3;
//        System.out.println(subProcess(nums, target));


    }

    private static int subProcess(int[] nums, int target){
        //第一个数必定为正！！这才是真正的target
        target = target - nums[0];

        int[] real_nums = new int[nums.length - 1];
        for (int i = 0; i < real_nums.length; i++) {
            real_nums[i] = nums[i+1];
        }
        Arrays.sort(real_nums);

        int sum = 0;
        for(int num : real_nums){
            sum += num;
        }
        if(sum < target || (sum + target) % 2 == 1){
            return 0;
        }

        int W = (sum + target) / 2;
        int[] dp = new int[W + 1];
        dp[0] = 1;

        for(int num : real_nums){
            for (int j = W; j >= 1; j--) {
                if (j >= num) {
                    dp[j] = dp[j] + dp[j - num];
                }
            }
        }
        return dp[W];

    }
}
