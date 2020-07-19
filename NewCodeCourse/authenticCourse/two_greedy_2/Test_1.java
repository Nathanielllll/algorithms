package authenticCourse.two_greedy_2;
/*
怪兽：
能力数组：[6,9,3...]
贿赂钱数组：[3,5,100...]
用钱去贿赂，成为你的保镖，你的能力会累加上怪兽的能力
如果你的能力<怪兽的能力，一定要贿赂
如果你的能力>=怪兽的能力，你可以直接通过，也可以选择贿赂来增加你的能力
要求：0~N-1通关，看你最少花多少钱

动态规划：dp[i][j]，人从0~i通关能力为j的时候，你需要花的钱。
那么最后的一行表示：在达到最后的一关，在能力为0,1,...,j的时候，需要花的钱
不能这样通关就是-1，能通关就是正数。选择最后一行的最小正数，就是答案

逻辑分支：
arr[i] 为能力数组
1）若j<arr[i],dp[i][j]=-1
2）若j>=arr[i],有选择。不贿赂dp[i-1][j]；贿赂dp[i-1][j-arr[i]]+money[i]--->0/1背包问题？选二者的最小值，当然如果是-1，则直接舍弃
 */
public class Test_1 {
    /**
     *
     * @param d d[i]：i号怪兽的能力
     * @param p p[i]：i号怪兽要求的钱
     * @param hp 当前你所具有的能力
     * @param index 来到了第index个怪兽的面前
     * @return
     */
    public static long process(int[] d, int[] p, int hp, int index){
        if (index == d.length) {
            return 0;
        }
        if(hp < d[index]){
            return p[index] + process(d, p, hp + d[index], index + 1);
        }else {
            return Math.min(
                    p[index] + process(d, p, hp + d[index], index + 1),
                    process(d, p, hp, index + 1));
        }
    }
}
