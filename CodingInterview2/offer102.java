public class offer102 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum < target || ((sum + target) & 1) == 1) {
            return 0;
        }
        int w = (sum + target) / 2;
        if (w < 0) {
            return 0;
        }

        int[] dp = new int[w + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = w; j >= num; j--) {
                dp[j] = dp[j] + dp[j - num];
            }
        }
        return dp[w];
    }

}
