package twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 三数之和为0。
 * break是结束整个循环体，continue是结束单次循环
 */
public class Test_15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < nums.length - 2; i++) {
            if ((nums[i] + nums[i + 1] + nums[i + 2]) > 0)
                break;
            //若i和前三大的数之和都小于target，那i肯定太小，遍历下一个
            if ((nums[i] + nums[len - 1] + nums[len - 2]) < 0)
                continue;

            //注意：答案中不可以包含重复的三元组。因为需要跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left != right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    while (left != right && nums[right] == nums[--right]) ;
                } else if (sum < 0) {
                    while (left != right && nums[left] == nums[++left]) ;
                } else {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    while (left != right && nums[right] == nums[--right]) ;
                    while (left != right && nums[left] == nums[++left]) ;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }
}
