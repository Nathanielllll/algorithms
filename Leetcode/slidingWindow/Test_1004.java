package slidingWindow;

public class Test_1004 {
    /**
     * 1004. 最大连续1的个数 III
     * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * 输出：6
     * 解释：[1,1,1,0,0,1,1,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
     * 示例 2：
     * <p>
     * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
     * 输出：10
     * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
     */

    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int length = nums.length;
        int zeroCnt = 0;
        int result = Integer.MIN_VALUE;
        while (right < length) {
            int right_num = nums[right];
            if (right_num == 0) {
                zeroCnt += 1;
            }

            ++right;
            while (zeroCnt > k) {
                int left_num = nums[left];
                if (left_num == 0) {
                    zeroCnt -= 1;
                }
                ++left;
            }

            result = Math.max(result, right - left);
        }
        return result == Integer.MIN_VALUE ? length : result;
    }

    public static void main(String[] args) {
        // 2, 11
        int[] nums = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k = 3;

//        int[] nums = {1,1,1,0,0,0,1,1,1,1,0};
//        int k = 2;

//        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1};
//        int k = 0;
        System.out.println(longestOnes(nums, k));
    }

}
