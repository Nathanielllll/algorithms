package dp.zeroOneBag;

/**
 * 在计算机界中，我们总是追求用有限的资源获取最大的收益。
 *
 * 现在，假设你分别支配着 m 个0和 n 个1。另外，还有一个仅包含0和1字符串的数组。
 *
 * 你的任务是使用给定的m 个0和 n 个1，找到能拼出存在于数组中的字符串的最大数量。每个0和1至多被使用一次。
 *
 * 注意:
 *
 * 给定0和1的数量都不会超过100。
 * 给定字符串数组的长度不会超过600。
 * 示例 1:
 *
 * 输入: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * 输出: 4
 *
 * 解释: 总共 4 个字符串可以通过 5 个 0 和 3 个 1 拼出，即 "10","0001","1","0" 。
 * 示例 2:
 *
 * 输入: Array = {"10", "0", "1"}, m = 1, n = 1
 * 输出: 2
 *
 * 解释: 你可以拼出 "10"，但之后就没有剩余数字了。更好的选择是拼出 "0" 和 "1" 。
 * zeros, ones
 * dp[k][i][j] = max(dp[k-1][i-zeros][j-ones](选了) + 1, dp[k-1][i][j](没选))
 */
public class Test_474 {
    public static int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int zeros = 0, ones = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zeros++;
                } else if (c == '1') {
                    ones++;
                }
            }

            for (int i = m; i >= 1; i--) {
                for (int j = n; j >= 1; j--) {
                    if (i >= zeros && j >= ones) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        System.out.println(findMaxForm(strs, m, n));
    }
}
