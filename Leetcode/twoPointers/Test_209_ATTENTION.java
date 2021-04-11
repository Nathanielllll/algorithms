package twoPointers;

/**
 * 给定一个含有n个正整数的数组和一个正整数s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例:
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组[4,3]是该条件下的长度最小的连续子数组。
 *
 * 有点像滑动窗口！
 */
public class Test_209_ATTENTION {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int sum = nums[left];
        int minLen = Integer.MAX_VALUE;

        while (right < nums.length) {
            if (sum < s) {
                if (++right < nums.length) {
                    sum += nums[right];
                }
            } else {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

}
