package twoPointers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个包含n 个整数的数组nums和一个目标值target，判断nums中是否存在四个元素 a，b，c和 d，使得a + b + c + d的值与target相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class Test_18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //优化，若i的连续四数之和大于target，后面肯定没有符合题意的组合，直接跳出
            if ((nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3]) > target)
                break;
            //若i和前三大的数之和都小于target，那i肯定太小，遍历下一个
            if ((nums[i] + nums[len - 1] + nums[len - 2] + nums[len - 3]) < target)
                continue;

//            //不能加
//            if (nums[i] > target) {
//                break;
//            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                //针对特殊情况优化，同上
                if ((nums[i] + nums[j] + nums[j + 1] + nums[j + 2]) > target)
                    break;
                if ((nums[i] + nums[j] + nums[len - 1] + nums[len - 2]) < target)
                    continue;

                //这个不能加
//                if (nums[j] > target) {
//                    break;
//                }

                /**当然是从j+1开始！！*/
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target) {
                        while (left < right && nums[right] == nums[--right]) ;
                    } else if (sum < target) {
                        while (left < right && nums[left] == nums[++left]) ;
                    } else {
                        List<Integer> list = new LinkedList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        result.add(list);
                        while (left < right && nums[right] == nums[--right]) ;
                        while (left < right && nums[left] == nums[++left]) ;
                    }
                }
            }
        }
        return result;
    }
}
