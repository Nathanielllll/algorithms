package dp;

public class Test_375 {
    /**
     * 我们正在玩一个猜数游戏，游戏规则如下：
     *
     * 我从1到 n 之间选择一个数字。
     * 你来猜我选了哪个数字。
     * 如果你猜到正确的数字，就会 赢得游戏 。
     * 如果你猜错了，那么我会告诉你，我选的数字比你的 更大或者更小 ，并且你需要继续猜数。
     * 每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。如果你花光了钱，就会 输掉游戏 。
     * 给你一个特定的数字 n ，返回能够 确保你获胜 的最小现金数，不管我选择那个数字 。
     *
     */
    private static int[][] cache = new int[201][201];
    public int getMoneyAmount(int n) {
        return dfs(1, n);
    }

    private int dfs(int left, int right){
        if (left >= right) {
            return 0;
        }

        if (cache[left][right] != 0) {
            return cache[left][right];
        }

        int curMin = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {
            int cur = Math.max(dfs(left, i - 1), dfs(i + 1, right)) + i;
            curMin = Math.min(cur, curMin);
        }

        return cache[left][right] = curMin;
    }
}
