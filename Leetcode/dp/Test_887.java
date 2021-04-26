package dp;

import java.util.Arrays;

/*
你将获得K个鸡蛋，并可以使用一栋从1到N共有 N层楼的建筑。

每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。

你知道存在楼层F ，满足0 <= F <= N 任何从高于 F的楼层落下的鸡蛋都会碎，从F楼层或比它低的楼层落下的鸡蛋都不会破。

每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层X扔下（满足1 <= X <= N）。

你的目标是确切地知道 F 的值是多少。

无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？



示例 1：

输入：K = 1, N = 2
输出：2
解释：
鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
如果它没碎，那么我们肯定知道 F = 2 。
因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
示例 2：

输入：K = 2, N = 6
输出：3
示例 3：

输入：K = 3, N = 14
输出：4
 */
public class Test_887 {
    private int[][] dp;

    public int superEggDrop(int K, int N) {
        dp = new int[K + 1][N + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return dfs(K, N);
    }

    private int dfs(int k, int n) {
        if (k == 0) return 0;
        if (k == 1) return n;
        if (n <= 1) return n;
        if (dp[k][n] != Integer.MAX_VALUE) {
            return dp[k][n];
        }
        int left = 1;
        int right = n + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            // 碎了，没碎
            if (dfs(k - 1, mid - 1) >= dfs(k, n - mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        dp[k][n] = 1 + Math.max(dfs(k - 1, left - 1), dfs(k, n - left));
        return dp[k][n];
    }
}
