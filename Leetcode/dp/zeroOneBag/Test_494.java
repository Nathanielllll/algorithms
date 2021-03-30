package dp.zeroOneBag;

/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例 1:
 *
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *
 *
 * 该问题可以转换为 Subset Sum 问题，从而使用 0-1 背包的方法来求解。
 * 可以将这组数看成两部分，P 和 N，其中 P 使用正号，N 使用负号，有以下推导：
 *
 *                   sum(P) - sum(N) = target
 * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
 *                        2 * sum(P) = target + sum(nums)
 *
 * dp[i][j] = dp[i-1][j](没有选为正) + dp[i-1][j-nums[i]](选为正)
 */
public class Test_494 {
    public static int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int num : nums) sum+=num;

        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        int W = (sum + S )/2;
        int[] dp = new int[W+1];
        dp[0] = 1;
        for(int num : nums){
            for (int i = W; i >= num; i--) {
                dp[i] = dp[i] + dp[i-num];
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        System.out.println(findTargetSumWays(nums, 3));
    }
}
