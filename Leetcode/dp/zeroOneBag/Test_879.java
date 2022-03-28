package dp.zeroOneBag;

/**
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 *
 * 第i种工作会产生profit[i]的利润，它要求group[i]名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 *
 * 工作的任何至少产生minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 *
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模10^9 + 7的值。
 *
 * 示例 1：
 *
 * 输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * 输出：2
 * 解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
 * 总的来说，有两种计划。
 * 示例 2：
 *
 * 输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * 输出：7
 * 解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
 * 有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。
 */
public class Test_879 {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int MOD = 1000_000_000 + 7;

        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;
        int N = group.length;
        for (int i = 0; i < N; i++) {
            int curPersons = group[i];
            int curProfit = profit[i];

            for (int j = n; j >= 0 && j >= curPersons; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] += dp[j - curPersons][Math.max(k - curProfit, 0)];
                    if(dp[j][k] > MOD){
                        dp[j][k] -= MOD;
                    }
                }
            }
        }

        int sum = 0;
        // dp里边的人数是指正好用多少个人，而总方案是只要人够用就行，所以最后要把符合收益的所有人数的结果加起来
        for (int i = 0; i <= n; i++) {
            sum += dp[i][minProfit];
            if (sum > MOD) {
                sum -= MOD;
            }
        }

        return sum;
    }


}
