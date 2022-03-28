package bit;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 * 
 *
 * 示例:
 *
 * 输入: a = 1, b = 1
 * 输出: 2
 */
public class Code_65 {
    public int add(int a, int b) {
        int result = 0;
        while (b != 0) {
            int temp = (a ^ b);
            int carry = (a & b) << 1;

            a = temp;
            b = carry;
        }
        return a;
    }
}
