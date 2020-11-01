package meituan.October_11;

import java.util.Arrays;
import java.util.Scanner;

public class Test_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            int[] nums = new int[2 * N];
            if(N == 1 && nums[0] >= 0){
                System.out.println(nums[0]);
            }
            for (int i = 0; i < N; i++) {
                nums[i] = nums[i+N] = scanner.nextInt();
            }
            System.out.println(maxSubArray(nums));
        }
    }

    private static int maxSubArray(int[] nums){
        int start = 0;
        int end = 0;
        int ans = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length-1; i++) {
            int num = nums[i];
            if(sum > 0){
                sum += num;
                end = i;
            }else {
                sum = num;
                start = i;
            }
            if(end - start < nums.length / 2){
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }
}
