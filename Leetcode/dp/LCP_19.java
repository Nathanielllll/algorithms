package dp;

/*
小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves，
字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。
每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。
请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。

示例 1：

输入：leaves = "rrryyyrryyyrr"

输出：2

解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"

示例 2：

输入：leaves = "ryr"

输出：0

解释：已符合要求，不需要额外操作

https://leetcode-cn.com/problems/UlBDOe/solution/qiu-xie-shou-cang-ji-by-leetcode-solution/
 */
public class LCP_19 {
    public static int minimumOperations(String leaves) {
        int length = leaves.length();
        int[][] dp = new int[length][3];

        int isRed;
        int isYellow = leaves.charAt(0) == 'y' ? 1 : 0;
        // 如果是黄色，则需要改变一次
        dp[0][0] = isYellow;
        // 这三个位置必然不可能，因此用极大值代替
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;

        for (int i = 1; i < length; i++) {
            isRed = leaves.charAt(i) == 'r' ? 1 : 0;
            isYellow = leaves.charAt(i) == 'y' ? 1 : 0;

            // 0状态时，前提条件是前面一个也是0状态，才能改变当前树叶
            dp[i][0] = dp[i - 1][0] + isYellow;
            // 1状态时，前提条件是前面一个也是1状态/前面一个是0状态
            dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]) + isRed;
            // 2状态时，前提条件是前面一个也是2状态/前面一个是1状态，并且位置必然要>=2
            if (i >= 2) {
                dp[i][2] = Math.min(dp[i - 1][2], dp[i - 1][1]) + isYellow;
            }

        }
        return dp[length - 1][2];
    }

    public static void main(String[] args) {
        System.out.println(minimumOperations("ryr"));
    }
}
