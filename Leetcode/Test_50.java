/**
 * 实现pow(x, n)，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 <x< 100.0
 * n是 32 位有符号整数，其数值范围是[−231,231− 1] 。
 *
 */
public class Test_50 {

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        long exponent = Math.abs((long) n);
        double result = exponentPow(x, exponent);

        return n < 0 ? 1 / result : result;
    }

    private double exponentPow(double base, long exponent){
        if (exponent == 1) {
            return base;
        } else if (exponent == 0) {
            return 1;
        }

        double sub = exponentPow(base, exponent >> 1);

        if (exponent % 2 == 0) {
            return sub * sub;
        }else {
            return sub * sub * base;
        }
    }

    public static void main(String[] args) {
        Test_50 solution = new Test_50();
        System.out.println(solution.myPow(2.1, 3));
    }

}
