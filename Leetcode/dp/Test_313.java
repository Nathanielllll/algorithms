package dp;

/**
 * @author luzhi
 * @date 2021/3/29
 */
public class Test_313 {
    /*
    编写一段程序来查找第 n 个超级丑数。

    超级丑数是指其所有质因数都是长度为k的质数列表primes中的正整数。

    示例:

    输入: n = 12, primes = [2,7,13,19]
    输出: 32
    解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
     */
    public static void main(String[] args) {
        int n = 12;
        int[] primes = {2,7,13,19};
        System.out.println(nthSuperUglyNumber(n, primes));
    }

    public static int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        dp[0] = 1;

        int[] indexes = new int[primes.length];

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < indexes.length; j++) {
                int index = indexes[j];
                min = Math.min(min, dp[index] * primes[j]);
            }

            for (int j = 0; j < indexes.length; j++) {
                int index = indexes[j];
                if(min == dp[index] * primes[j]){
                    indexes[j]++;
                }
            }

            dp[i] = min;
        }
        return dp[n - 1];
    }
}
