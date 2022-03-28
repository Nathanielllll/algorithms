package binarySearch;

/**
 * @author luzhi
 * @date 2021/4/15
 */
public class FindDifference {
    /*
    给定一个非空整数有序数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
    首先应该考虑的是时间复杂度和空间复杂度，且是有序数组，我首先想到的是二分查找，时间复杂度为O(logn)。
     */

    // nums已经是有序的了
    public static int singleNumber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (mid == 0 && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }
            if (mid == nums.length - 1 && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            }
            if (mid > 0 && mid < nums.length - 1 &&
                    nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            }

            if (mid % 2 == 0) {
                if (nums[mid] != nums[mid + 1]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid - 1] != nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
