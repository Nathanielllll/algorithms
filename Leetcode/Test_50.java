/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 */
public class Test_50 {

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }

        int exponent = Math.abs(n);
        double result = exponentPow(x, exponent);

        if (n < 0) {
            result = 1 / result;
        }

        return result;
    }

    private double exponentPow(double base, int exponent){
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }

        if (base == 1.0f)
            return 1;

        if (exponent == -2147483648) {
            return 0;
        }

        double result = exponentPow(base, exponent >> 1);
        result *= result;

        if (exponent % 2 == 1) {
            result *= base;
        }
        return result;
    }

    public static void main(String[] args) {
        Test_50 solution = new Test_50();
        System.out.println(solution.myPow(2.1, 3));
    }

}
