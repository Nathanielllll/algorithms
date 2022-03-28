public class offer090 {
    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }

        int[] nums1 = new int[n - 1];
        System.arraycopy(nums, 0, nums1, 0, n - 1);

        int[] nums2 = new int[n - 1];
        System.arraycopy(nums, 1, nums2, 0, n - 1);

        int p1 = process(nums1);
        int p2 = process(nums2);
        return Math.max(p1, p2);
    }

    public static int process(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 3};
        System.out.println(rob(nums));
    }
}
