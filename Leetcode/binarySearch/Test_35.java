package binarySearch;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class Test_35 {
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int middle = (start + end) >> 1;
            if (nums[middle] == target) {
                return middle;
            } else {
                if (nums[middle] > target) {
                    if (middle > 0 && nums[middle - 1] < target) {
                        return middle;
                    } else if (middle == 0) {
                        return 0;
                    }else {
                        end = middle - 1;
                    }
                }else if (nums[middle] < target) {
                    if (middle < nums.length - 1 && nums[middle + 1] > target){
                        return middle + 1;
                    } else if (middle == nums.length - 1) {
                        return nums.length;
                    } else {
                        start = middle + 1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,7};
        int target = 0;
        System.out.println(searchInsert(nums, target));
    }
}
