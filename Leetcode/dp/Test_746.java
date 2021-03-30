package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。

每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。

请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。


示例1：

输入：cost = [10, 15, 20]
输出：15
解释：最低花费是从 cost[1] 开始，然后走两步即可到阶梯顶，一共花费 15 。
示例 2：

输入：cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
输出：6
解释：最低花费方式是从 cost[0] 开始，逐个经过那些 1 ，跳过 cost[3] ，一共花费 6 。
 */
public class Test_746 {
    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};

        System.out.println(minCostClimbingStairs(cost));
    }

    public static int minCostClimbingStairs(int[] cost) {
        List<Integer> list = new ArrayList<>();
        for (int j : cost) {
            list.add(j);
        }
        list.add(0);
        int[] dp = new int[cost.length + 1];

        for (int i = 0; i < 2; i++) {
            dp[i] = list.get(i);
        }
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + list.get(i);
        }
        return dp[cost.length];
    }

}
