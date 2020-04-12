import java.util.ArrayList;
import java.util.List;

/**
 * 和为s的两个数字。
 * 输入一个递增排序的数组和一个数字s，在数组中找到两个数，使得他们的和正好是s。
 * <p>
 * 使用双指针。
 */
public class Code_57 {
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return new int[]{};
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int curSum = nums[left] + nums[right];
            if (curSum == target) {
                int[] res = {nums[left], nums[right]};
                return res;
            } else if (curSum > target) {
                while(left < right && nums[right] == nums[--right]);
            } else {
                while(left < right && nums[left] == nums[++left]);
            }
        }

        return new int[]{};
    }

}
