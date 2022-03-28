package dp.zeroOneBag;

import java.util.Arrays;

public class Test_322 {
    /**
     * 因为硬币可以重复使用，因此这是一个完全背包问题。完全背包只需要将 0-1 背包的逆序遍历 dp 数组改为正序遍历即可。
     */
    public int coinChange(int[] coins, int amount) {
        //dp表示凑成总金额所需的最少的硬币个数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            //dp[i] = amount + 1;
            for (int coin : coins){
                if (i - coin >= 0) {
                    //如果不选coin/选了coin
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        //两个都可以的！！
        for (int coin : coins){
            for (int i = 1; i <= amount; i++) {
                if (i - coin >= 0) {
                    //如果不选coin/选了coin
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

}
