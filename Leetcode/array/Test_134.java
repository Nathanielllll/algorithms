package array;

import java.util.Arrays;

/*
    在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升。

    你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。

    如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

    说明:

    如果题目有解，该答案即为唯一答案。
    输入数组均为非空数组，且长度相同。
    输入数组中的元素均为非负数。
    示例1:

    输入:
    gas  = [1,2,3,4,5]
    cost = [3,4,5,1,2]

    输出: 3

    解释:
    从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
    开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
    开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
    开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
    开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
    开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
    因此，3 可为起始索引。
     */
public class Test_134 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) {
            return -1;
        }
        int n = gas.length;
        int total = 0;
        int result = 0;
        for (int i = 0; i < n; i++) {
            total += gas[i] - cost[i];
            if (total < 0) {
                total = 0;
                result = i + 1;
            }
        }
        return result;
    }

//    public static int canCompleteCircuit(int[] gas, int[] cost) {
//        int len = gas.length;
//
//        int index = 0;
//        outer:
//        while (index < gas.length) {
//            int cur_gas = 0;
//            for (int j = index; j <= index + gas.length; j++) {
//                cur_gas = cur_gas + gas[j % len] - cost[j % len];
//                if (cur_gas < 0) {
//                    /**
//                     相比于canCompleteCircuit_1的改进点：
//                     当考虑 i 能到达的最远的时候，假设是 j。
//                     那么 i + 1 到 j 之间的节点是不是就都不可能绕一圈了？
//
//                     反证法：假设 i + 1 的节点能绕一圈，那么就意味着从 i + 1 开始一定能到达 j + 1。又因为从 i 能到达 i + 1，所以从 i 也能到达 j + 1。
//                     但事实上，i 最远到达 j 。产生矛盾，所以 i + 1 的节点一定不能绕一圈。同理，其他的也是一样的证明。
//                     所以下一次的 i 我们不需要从 i + 1 开始考虑，直接从 j + 1 开始考虑即可。
//
//                     作者：windliang
//                     链接：https://leetcode-cn.com/problems/gas-station/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--30/
//                     来源：力扣（LeetCode）
//                     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
//                     */
//                    index = j + 1;
//
//                    continue outer;
//                } else if (j == index + gas.length) {
//                    return index % gas.length;
//                }
//            }
//            index++;
//        }
//
//        return -1;
//    }
//
//    public static int canCompleteCircuit_1(int[] gas, int[] cost) {
//        int len = gas.length;
//
//        int index = 0;
//        outer:
//        while (index < gas.length) {
//            int cur_gas = 0;
//            for (int j = index; j <= index + gas.length; j++) {
//                cur_gas = cur_gas + gas[j % len] - cost[j % len];
//                if (cur_gas < 0) {
//                    continue outer;
//                } else if (j == index + gas.length) {
//                    return index % gas.length;
//                }
//            }
//            index++;
//        }
//
//        return -1;
//    }
}
