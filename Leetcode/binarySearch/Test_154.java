package binarySearch;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组[0,1,2,4,5,6,7] 可能变为[4,5,6,7,0,1,2])。
 * <p>
 * 请找出其中最小的元素。
 * <p>
 * 注意数组中可能存在重复的元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入: [1,3,5]
 * 输出: 1
 * 示例2：
 * <p>
 * 输入: [2,2,2,0,1]
 * 输出: 0
 */
public class Test_154 {
    public static int findMin(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        if (nums.length == 1 || nums[left] < nums[right]) {
            return nums[0];
        }

        if (nums[left] == nums[right]) {
            int result = nums[left];
            for (int i = left + 1; i <= right; i++) {
                if (result > nums[i]) {
                    result = nums[i];
                }
            }
            return result;
        }

        while (left <= right) {
            int mid = (left + right) >> 1;

            /**
             * 注意这两个退出条件
             * 即左边的数字要比右边的数字大！然后退出右边的数字！
             */
            if (nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }
            if (nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            /**
             * 注意！这里一定是>=，不能是>
             */
            if (nums[mid] >= nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
