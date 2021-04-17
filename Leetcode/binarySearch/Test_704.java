package binarySearch;

/**
 * @author luzhi
 * @date 2021/4/15
 */
public class Test_704 {
    public static int search(int[] nums, int target) {
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
}
