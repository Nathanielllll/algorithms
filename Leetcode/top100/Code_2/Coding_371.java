package top100.Code_2;

/**
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: a = -2, b = 3
 * 输出: 1
 * <p>
 * a + b 的问题拆分为 (a 和 b 的无进位结果) + (a 和 b 的进位结果)
 * 无进位加法使用异或运算计算得出
 * 进位结果使用与运算和移位运算计算得出
 * 循环此过程，直到进位为 0
 */
public class Coding_371 {
    public static int getSum(int a, int b) {
        //当进位不为0的时候，一直循环即可。
        while (b != 0) {
            //a 和 b 的进位结果
            int carry = (a & b) << 1;
            //a 和 b 的无进位结果
            a = a ^ b;

            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(getSum(1, 2));
        System.out.println(getSum(-2, 3));
    }
}
