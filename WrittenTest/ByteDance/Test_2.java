package ByteDance;

import java.util.*;

/*
5
3 5 13 9 12

1

 */
public class Test_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = scanner.nextInt();
        }
        int res = subProcess(nums);
        System.out.println(res);

//        int[] nums = {3, 5, 13, 9, 12};
//        System.out.println(subProcess(nums));

    }

    public static int subProcess(int[] nums){
        int ans = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if(nums[i + 1] >= nums[i]){
                continue;
            }
            int num1 = (nums[i] - 1) / nums[i + 1];
            ans += num1;
            nums[i] /= (num1 + 1);
        }
        return ans;
    }
}
