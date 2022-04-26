package binarySearch;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组[0,1,2,4,5,6,7]可能变为[4,5,6,7,0,1,2])。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回-1。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是O(logn) 级别。
 * <p>
 * 示例 1:
 * <p>               middle
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class Test_33 {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            // 0~mid是有序的
            if (nums[0] <= nums[mid]) {
                // 如果target在0~mid中间
                if (nums[0] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // mid~n-1是有序的
            else {
                if (nums[mid] < target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(search(nums, 0));
    }
}
