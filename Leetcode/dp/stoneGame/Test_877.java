package dp.stoneGame;

import java.util.Arrays;

/*
亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。

游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。

亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。

假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。

 

示例：

输入：[5,3,4,5]
输出：true
解释：
亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
 */
public class Test_877 {

    // dp 填表的顺序，注意可以通过画图去实现。其实思想来说，是从dfs + memo得到的
    public boolean stoneGame_1(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                if (i == j) {
                    continue;
                }
                int chooseLeft = piles[i] - dp[i + 1][j];
                int chooseRight = piles[j] - dp[i][j - 1];
                dp[i][j] = Math.max(chooseLeft, chooseRight);
            }
        }
        return dp[0][length - 1] > 0;
    }


    // dfs + memo
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        // 定义为：左边索引为left，右边索引为right时候的值
        int[][] memo = new int[length][length];
        for (int i = 0; i < length; i++) {
            // 由于是相对分数，有可能是在负值里面选较大者，因此初始化的时候不能为 0
            Arrays.fill(memo[i], Integer.MIN_VALUE);
            memo[i][i] = piles[i];
        }
        return dfs(piles, 0, piles.length - 1, memo) > 0;
    }

    private static int dfs(int[] piles, int left, int right, int[][] memo) {
        if (left == right) {
            return piles[left];
        }
        if (memo[left][right] != Integer.MIN_VALUE) {
            return memo[left][right];
        }

        int chooseLeft = piles[left] - dfs(piles, left + 1, right, memo);
        int chooseRight = piles[right] - dfs(piles, left, right - 1, memo);
        int res = Math.max(chooseLeft, chooseRight);
        memo[left][right] = res;
        return res;
    }
}
