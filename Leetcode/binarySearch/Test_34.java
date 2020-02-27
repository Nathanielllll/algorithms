package binarySearch;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
public class Test_34 {
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return new int[] {-1, -1};
        }
        //求第一个
        int first = searchFirst(nums, target, 0, nums.length-1);
        //求第二个
        int last = searchLast(nums, target, 0, nums.length-1);
        return new int[] {first, last};
    }

    /**一定要注意细节，如while (start <= end)    0～nums.length-1*/
    private static int searchFirst(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int middle = (start + end) / 2;
            if (nums[middle] < target) {
                start = middle + 1;
            } else if (nums[middle] > target) {
                end = middle - 1;
            } else {
                if (middle > 0 && nums[middle] == nums[middle - 1]) {
                    end = middle - 1;
                } else if(middle == 0 || nums[middle] != nums[middle - 1]){
                    return middle;
                }
            }
        }
        return -1;
    }

    private static int searchLast(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int middle = (start + end) / 2;
            if (nums[middle] < target) {
                start = middle + 1;
            } else if (nums[middle] > target) {
                end = middle - 1;
            } else {
                if (middle < nums.length - 1 && nums[middle] == nums[middle + 1]) {
                    start = middle + 1;
                } else if(middle == nums.length - 1 || nums[middle] != nums[middle + 1]){
                    return middle;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 18;
        System.out.println(Arrays.toString(searchRange(null, 0)));
    }
}
