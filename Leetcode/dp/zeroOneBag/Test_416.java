package dp.zeroOneBag;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 *
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 *
 * 1、不选择 nums[i]，如果在 [0, i - 1] 这个子区间内已经有一部分元素，使得它们的和为 j ，那么 dp[i][j] = dp[i - 1][j]；
 * 2、选择 nums[i]，如果在 [0, i - 1] 这个子区间内就得找到一部分元素，使得它们的和为 j - nums[i]. dp[i][j] = dp[i - 1][j - nums[i]]。
 *
 * 状态转移方程是：
 * dp[i][j] = dp[i - 1][j] or dp[i - 1][j - nums[i]]
 */
public class Test_416 {
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;
        }
        int W = sum / 2;

        boolean[] dp = new boolean[W + 1];
        dp[0] = true;

        //先遍历物品，后遍历 重量？
        for (int num : nums) {
            for (int j = W; j >= 1; j--) {
                if(j >= num){
                    dp[j] = dp[j] || dp[j - num];
                }
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 5};
        System.out.println(canPartition(nums));
    }
}
