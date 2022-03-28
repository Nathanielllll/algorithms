public class offer091 {
    public int minCost(int[][] costs) {
        int row = costs.length;
        int col = costs[0].length;
        int[][] dp = new int[row][col];

        System.arraycopy(costs[0], 0, dp[0], 0, 3);

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][j];
                }
                if (j == 1) {
                    dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][j];
                }
                if (j == 2) {
                    dp[i][j] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][j];
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++) {
            min = Math.min(min, dp[row - 1][j]);
        }
        return min;
    }
}
