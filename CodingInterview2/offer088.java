public class offer088 {
    private static int getCost(int[] cost, int startIdx) {
        int sum = 0;
        for (int num : cost) {
            sum += num;
        }

        int n = cost.length;
        int[] dp = new int[n];

        for (int i = startIdx + 1; i < n; i++) {
            dp[i] = sum;
        }
        for (int i = startIdx; i < n; i++) {
            for (int j = 1; j <= 2 && i - j >= startIdx; j++) {
                int idx = i - j;
                dp[i] = Math.min(dp[i], dp[idx] + cost[idx]);
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] cost = {10, 15, 20};
        System.out.println(getCost(cost, 0));
    }

}
