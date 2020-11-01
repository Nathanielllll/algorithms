package bit;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 * <p>
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 */
public class Test_29 {
    public int divide(int dividend, int divisor) {
        //只有这种情况会溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        //直接返回就完了
        if (divisor == 1) {
            return dividend;
        }

        boolean isPositive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);

        long n1 = Math.abs((long) dividend);
        long n2 = Math.abs((long) divisor);

        long result = 0;

        while (n1 >= n2) {
            int shift = 0;
            while (n1 >= (n2 << shift)) {
                shift++;
            }
            n1 = n1 - (n2 << (shift - 1));
            result = result + (1 << (shift - 1));
        }

        return isPositive ? (int) result : (int) -result;
    }
}
