package dp.zeroOneBag;

/**
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 * <p>
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 * <p>
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *
 */

/** ATTENTION 对比518题和377题的区别！！！ */
public class Test_518 {
    public static int change(int amount, int[] coins) {
        if (coins == null) {
            return 0;
        }

        int[] dp = new int[amount + 1];
        dp[0] = 1;

//        for (int i = 1; i <= amount; i++) {
//            for (int coin : coins) {
//                if (i >= coin) {
//                    dp[i] = dp[i] + dp[i - coin];
//                }
//            }
//        }

        //上面的写法是错误的！可是为什么？？
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i >= coin) {
                    dp[i] = dp[i] + dp[i - coin];
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 3};
        int amount = 4;
        System.out.println(change(amount, coins));
    }
}
