package binarySearch;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回[-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
public class Test_34 {

    private int findFirst1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return nums[left] == target ? left : -1;
    }

    private int findLast1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }


    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return new int[] {-1, -1};
        }
        //求第一个
        int first = findFirst1(nums, target);
        if (first == -1) {
            return new int[] {-1, -1};
        }
        //求第二个
        int last = findLast1(nums, target);
        return new int[] {first, last};
    }

    private int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }else {
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    private int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }else {
                if (mid == nums.length - 1 || nums[mid + 1] != target) {
                    return mid;
                }else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
