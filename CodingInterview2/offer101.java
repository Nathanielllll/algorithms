public class offer101 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 奇数，则为false
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int i = target; i >= 0; i--) {
                if (i - num >= 0) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
