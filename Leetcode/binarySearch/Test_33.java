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
        int n = nums.length;
        if (n == 0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;

        int left = 0;
        int right = nums.length - 1;
        // 第一次「二分」：从中间开始找，找到满足 >=nums[0] 的分割点（旋转点）
        // 查找旋转点的idx。本题中有：nums[idx] >= nums[0]
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            // 往左找
            if (nums[mid] >= nums[0]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(nums[left]);

        // 第二次「二分」：通过和 nums[0] 进行比较，得知 target 是在旋转点的左边还是右边
        if (target >= nums[0]) {
            left = 0;
        } else {
            left = left + 1;
            right = nums.length - 1;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return nums[right] == target ? right : -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3};
        System.out.println(search(nums, 0));
    }
}
