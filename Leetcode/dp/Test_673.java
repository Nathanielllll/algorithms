package dp;

/**
 * 给定一个未排序的整数数组knumsk，k返回最长递增子序列的个数k。
 *
 * 注意k这个数列必须是 严格 递增的。
 *
 * k
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * k
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test_673 {
    public static int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int maxLength = 1;
        // 以当前idx为结尾时，最大长度、出现次数
        int[][] dp = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = 1;
            }
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j][0] + 1 > dp[i][0]) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];
                    } else if (dp[j][0] + 1 == dp[i][0]) {
                        dp[i][1] += dp[j][1];
                    }
                }
            }
            if (dp[i][0] > maxLength) {
                maxLength = dp[i][0];
            }
        }

        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i][0] == maxLength) {
                result += dp[i][1];
            }
        }
        return result;
    }
}
