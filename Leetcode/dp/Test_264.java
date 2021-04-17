package dp;

/**
 * 编写一个程序，找出第 n 个丑数。
 *
 * 丑数就是只包含质因数2, 3, 5 的正整数。
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 *
 * 1是丑数。
 * n不超过1690。
 */
public class Test_264 {
    public int nthUglyNumber(int index) {
        if (index < 1) {
            return -1;
        }

        int[] dp = new int[index];
        dp[0] = 1;

        int index_2 = 0;
        int index_3 = 0;
        int index_5 = 0;

        for (int i = 1; i < index; i++) {
            int num_2 = dp[index_2] * 2;
            int num_3 = dp[index_3] * 3;
            int num_5 = dp[index_5] * 5;
            int min = getMin(num_2, num_3, num_5);
            dp[i] = min;

            if (num_2 == min) {
                index_2++;
            }
            if (num_3 == min) {
                index_3++;
            }
            if (num_5 == min) {
                index_5++;
            }
        }
        return dp[index - 1];
    }

    public int getMin(int i, int j, int k){
        return Math.min(i, Math.min(j ,k));
    }
}
