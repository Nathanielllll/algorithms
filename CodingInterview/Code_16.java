/**
 *
 * 注意：用递归来计算
 *
 * 数值的整数次方，不考虑大数问题。
 * <p>
 * 要考虑周全。
 */
public class Code_16 {

    public double myPow(double x, int n) {
        // 特判，也可以认为是递归终止条件
        long N = n;
        if (N < 0) {
            return 1 / myPow(x, -N);
        }
        return myPow(x, N);
    }

    private double myPow(double x, long n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        // 根据指数是奇数还是偶数进行分类讨论
        // 使用位运算的 与 运算符代替了求余数运算

        double sub = myPow(x, n >> 1);
        if (n % 2 == 0) {
            return sub * sub;
        }else {
            return sub * sub * x;
        }
    }


}
