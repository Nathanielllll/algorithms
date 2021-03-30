package dp.stoneGame;

import java.util.Arrays;

/*
亚历克斯和李继续他们的石子游戏。许多堆石子排成一行，每堆都有正整数颗石子piles[i]。游戏以谁手中的石子最多来决出胜负。

亚历克斯和李轮流进行，亚历克斯先开始。最初，M = 1。

在每个玩家的回合中，该玩家可以拿走剩下的前X堆的所有石子，其中1 <= X <= 2M。然后，令M = max(M, X)。

游戏一直持续到所有石子都被拿走。

假设亚历克斯和李都发挥出最佳水平，返回亚历克斯可以得到的最大数量的石头。

示例：

输入：piles = [2,7,9,4,4]
输出：10
解释：
如果亚历克斯在开始时拿走一堆石子，李拿走两堆，接着亚历克斯也拿走两堆。在这种情况下，亚历克斯可以拿到 2 + 4 + 4 = 10 颗石子。
如果亚历克斯在开始时拿走两堆石子，那么李就可以拿走剩下全部三堆石子。在这种情况下，亚历克斯可以拿到 2 + 7 = 9 颗石子。
所以我们返回更大的 10。

 */
public class Test_1140 {
    public static void main(String[] args) {
        int[] piles = {2,7,9,4,4};
        System.out.println(stoneGameII(piles));
    }

    private static int[] preSum;
    public static int stoneGameII(int[] piles) {
        int length = piles.length;
        int[][] memo = new int[length][length + 1];
        preSum = new int[length + 1];
        for (int i = 0; i < length; i++) {
            preSum[i + 1] = preSum[i] + piles[i];
        }

        return (gapSum(0, length - 1) + dfs(piles, 0, 1, memo))/2;
    }

    private static int gapSum(int i, int j){
        return preSum[j + 1] - preSum[i];
    }

    private static int dfs(int[] piles, int begin, int M, int[][] memo){
        int length = piles.length;
        if (begin >= length) {
            return 0;
        }

        if (memo[begin][M] > 0) {
            return memo[begin][M];
        }

        if (length - begin <= 2 * M) {
            return memo[begin][M] = gapSum(begin, length - 1);
        }

        int minLength = Math.min(2 * M, length - begin);
        int best = Integer.MIN_VALUE;
        for (int x = 1; x <= minLength; x++) {
            int current = gapSum(begin, begin + x - 1) - dfs(piles, begin + x, Math.max(M, x), memo);
            best = Math.max(best, current);
        }
        return memo[begin][M] = best;
    }
}
