/**
 * @author luzhi
 * @date 2021/3/30
 */

public class Test_172 {
    /*
    给定一个整数 n，返回 n! 结果尾数中零的数量。

    示例 1:

    输入: 3
    输出: 0
    解释:3! = 6, 尾数中没有零。
    示例2:

    输入: 5
    输出: 1
    解释:5! = 120, 尾数中有 1 个零.
    说明: 你算法的时间复杂度应为O(logn)。
     */

    /*
    等价于计算n!当中有几个5，因为10=5*2，而2必然是更多的。
    count += n / 5;就是计算n的时候，有n/5个5。再去循环计算n/5的时候有多少个5，就是(n/5)/5
     */
    public static int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
