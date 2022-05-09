package dp.longestIncreasingSubsequence;

import java.util.Arrays;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是[2,3,7,101]，它的长度是 4。
 * <p>
 * <p>
 * 与最长递增子序列相比，最长公共子序列有以下不同点：
 * <p>
 * 针对的是两个序列，求它们的最长公共子序列。
 * <p>
 * 在最长递增子序列中，dp[i] 表示以 Si 为结尾的最长递增子序列长度，子序列必须包含 Si ；
 * 在最长公共子序列中，dp[i][j] 表示 S1 中前 i 个字符与 S2 中前 j 个字符的最长公共子序列长度，不一定包含 S1i 和 S2j。
 * <p>
 * 在求最终解时，最长公共子序列中 dp[N][M] 就是最终解，
 * 而最长递增子序列中 dp[N] 不是最终解，因为【以 SN 为结尾】的最长递增子序列不一定是整个序列最长递增子序列，需要遍历一遍 dp 数组找到最大者。
 */
public class Test_300 {
    public int lengthOfLIS(int[] nums) {
        //dp[i] 表示以 Si 为结尾的最长递增子序列长度，子序列必须包含 Si
        //不一定是整个序列最长递增子序列，需要遍历一遍 dp 数组找到最大者。
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 二分查找法
    public int lengthOfLIS01(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        int length = nums.length;
        int end = 0;

        // tail[i] 表示：长度为 i + 1 的 所有 上升子序列的结尾的最小值。

        // 首先需要证明tail是严格递增的！！！
        // 证明：即对于任意的下标 0 <= i < j < len ，都有 tail[i] < tail[j]。
        // 使用反证法：假设对于任意的下标 i < j ，存在某个 tail[i] >= tail[j]。
        // 对于此处的 tail[i] 而言，对应一个上升子序列 [a_0, a_1, ..., a_i]，依据定义 tail[i] = ai ；
        // 对于此处的 tail[j] 而言，对应一个上升子序列 [b_0, b_1, ..., b_i, ... , b_j]，依据定义 tail[j] = bj  ； 由于 tail[i] >= tail[j]，等价于 ai ≥ bj  。
        // 而在上升子序列 [b_0, b_1, ..., b_i, ... , b_j] 中，bi  严格小于 bj  ，
        // 故有 ai>=bj>bi， 则上升子序列 [b_0, b_1, ..., b_i]是一个长度也为 i + 1 但是结尾更小的数组，与 ai  的最小性矛盾。 因此原命题成立。（证完）
        int[] tail = new int[length];
        tail[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > tail[end]) {
                ++end;
                tail[end] = nums[i];
            } else {
                //nums[i] <= tail[end]，则找到在0~size中找到第一个大于等于nums[i]的idx
                int idx = binarySearch(tail, end, nums[i]);
                tail[idx] = nums[i];
            }
        }
        ++end;
        return end;
    }

    // 在0~size中找到第一个大于等于target的idx
    private int binarySearch(int[] nums, int size, int target) {
        int left = 0;
        int right = size;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
