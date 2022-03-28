package dp.longestIncreasingSubsequence;

import java.util.Arrays;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是[2,3,7,101]，它的长度是 4。
 *
 *
 * 与最长递增子序列相比，最长公共子序列有以下不同点：
 *
 * 针对的是两个序列，求它们的最长公共子序列。
 *
 * 在最长递增子序列中，dp[i] 表示以 Si 为结尾的最长递增子序列长度，子序列必须包含 Si ；
 * 在最长公共子序列中，dp[i][j] 表示 S1 中前 i 个字符与 S2 中前 j 个字符的最长公共子序列长度，不一定包含 S1i 和 S2j。
 *
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
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;

    }
}
