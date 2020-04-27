package dp;

/**
 * 剪绳子，动态规划
 * 给你一条长度n的绳子，把绳子剪成m段(n>1 && m>1)
 */
public class Code_14 {

    /**
     * 使用动态规划
     *
     * @param length
     * @return 题目要求至少剪一刀
     * 有公式为f(n)=max(f(i)*f(n-i)) 其中0<i<n
     */
    public static int maxProductAfterCutting_solution1(int length) {

        //几个特殊情况
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }

        int[] dp = new int[length + 1];
        //在length >= 4的情况下，这几个其实不可分割的（因为最大）
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        int max;
        for (int n = 4; n <= length; n++) {
            max = 0;
            //i <= n/2是因为实际上是对称的
            //此时需要用：公式为f(n)=max(f(i)*f(n-i)) 其中0<i<n
            for (int i = 1; i <= n / 2; i++) {
                int product = dp[i] * dp[n - i];
                max = Math.max(max, product);
            }
            dp[n] = max;
        }
        return dp[length];
    }

    /**
     * 贪心算法
     * 特殊情况，可以用贪心来算
     * 实际上就是分解成2或者3，而且3越多越好。
     *
     * @param length
     * @return
     */
    public static int maxProductAfterCutting_solution2(int length) {

        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }

        //只可能剩下0，1，2，其中1的情况较为特殊
        int numberOfThree = length / 3;

        //如7/3=2，length - numberOfThree * 3 = 1 || 3 2 2 = 12比较大；而不是3 3 1
        if (length - numberOfThree * 3 == 1) {
            numberOfThree--;
        }

        int numberOfTwo = (length - numberOfThree * 3) / 2;
        return (int) (Math.pow(3, numberOfThree) * Math.pow(2, numberOfTwo));
    }

    public static void main(String[] args) {

        //动态规划
        System.out.println(maxProductAfterCutting_solution1(3));
        System.out.println(maxProductAfterCutting_solution1(4));
        System.out.println(maxProductAfterCutting_solution1(5));
        System.out.println(maxProductAfterCutting_solution1(6));
        System.out.println(maxProductAfterCutting_solution1(8));

        System.out.println();
        //贪心算法
        System.out.println(maxProductAfterCutting_solution2(3));
        System.out.println(maxProductAfterCutting_solution2(4));
        System.out.println(maxProductAfterCutting_solution2(5));
        System.out.println(maxProductAfterCutting_solution2(6));
        System.out.println(maxProductAfterCutting_solution2(8));
    }
}
