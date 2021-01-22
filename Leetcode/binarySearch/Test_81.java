package binarySearch;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * <p>
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 * <p>
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class Test_81 {
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        if (nums.length == 1) {
            return nums[0] == target;
        }

        int left = 0;
        int right = nums.length - 1;
        // nums[0] == nums[nums.length-1]
        if (nums[left] == nums[right]) {
            for (int i = 0; i < right; i++) {
                if (nums[i] == target) {
                    return true;
                }
            }
            return false;
        }

        // nums[0] != nums[nums.length-1]
        while (left <= right) {
            int middle = (left + right) >> 1;
            if (nums[middle] == target) {
                return true;
            } else {
                // 证明middle在前子数组
                if (nums[middle] >= nums[left]) {
                    //target在nums[middle]~nums[middle]
                    if (target >= nums[left] && target <= nums[middle]) {
                        right = middle - 1;
                        //target不在nums[middle]~nums[middle]
                    } else {
                        left = middle + 1;
                    }
                // 证明middle在后子数组
                }else {
                    if (target <= nums[right] && target >= nums[middle]) {
                        left = middle + 1;
                    } else {
                        right = middle - 1;
                    }
                }

            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,3,1};
        System.out.println(search(nums, 1));
        System.out.println(search(nums, 3));
        System.out.println(search(nums, 7));
        System.out.println(search(nums, 6));
    }
}
