package dp.longestIncreasingSubsequence;

import java.util.Arrays;

/**
 * 给出n个数对。在每一个数对中，第一个数字总是比第二个数字小。
 *
 * 现在，我们定义一种跟随关系，当且仅当b < c时，数对(c, d)才可以跟在(a, b)后面。我们用这种形式来构造一个数对链。
 *
 * 给定一个对数集合，找出能够形成的最长数对链的长度。你不需要用到所有的数对，你可以以任何顺序选择其中的一些数对来构造。
 *
 * 示例 :
 *
 * 输入: [[1,2], [2,3], [3,4]]
 * 输出: 2
 * 解释: 最长的数对链是 [1,2] -> [3,4]
 */
public class Test_646 {
    public static int findLongestChain1(int[][] pairs) {
        if (pairs == null || pairs.length == 0) {
            return 0;
        }

        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int row = pairs.length;

        int[] dp = new int[row];
        Arrays.fill(dp, 1);
        int res = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < i; j++) {
                int i_0 = pairs[i][0];
                int j_1 = pairs[j][1];
                if (i_0 > j_1) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
