package dp.bestTimeToBuyAndSellStock;

/**
 * 给定一个整数数组，其中第i个元素代表了第i天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
 * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
 * 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
 *
 */
public class Test_309 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // 初始状态
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_0 = 0;
        for (int i = 0; i < prices.length; i++) {
            int temp = dp_i_0;//i-1
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);//i
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);//i
            dp_pre_0 = temp;
        }
        return dp_i_0;
    }
}
