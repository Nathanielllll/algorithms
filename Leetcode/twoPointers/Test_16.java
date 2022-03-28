package twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，
 * 使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class Test_16 {

    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return -1;
        }

        int minGap = Integer.MAX_VALUE;
        int sumClosest = Integer.MAX_VALUE;

        int len = nums.length;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                int curGap = Math.abs(sum - target);
                if (curGap < minGap) {
                    minGap = curGap;
                    sumClosest = sum;
                }

                if (sum > target) {
                    while (left < right && nums[right] == nums[--right]) ;
                } else if (sum < target) {
                    while (left < right && nums[left] == nums[++left]) ;
                } else {
                    //已经是gap为0了，没必要继续了
                    return target;
//                    while (left < right && nums[right] == nums[--right]) ;
//                    while (left < right && nums[left] == nums[++left]) ;
                }
            }
        }
        return sumClosest;
    }


    public static void main(String[] args) {
//        int[] nums = {-1,2,1,-4};
//        System.out.println(threeSumClosest(nums, 1));

        int[] nums_1 = {0,0,0};
        System.out.println(threeSumClosest(nums_1, 1));
    }
}
