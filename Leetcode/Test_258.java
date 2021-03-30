/**
 * @author luzhi
 * @date 2021/3/29
 */
public class Test_258 {
    /*
    给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。

    示例:

    输入: 38
    输出: 2
    解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
     */
    public static int addDigits_1(int num) {
        return (num - 1) % 9 + 1;
    }

    public static int addDigits(int num) {
        while (num >= 10) {
            int tmp = 0;
            while (num > 0) {
                tmp += num % 10;
                num /= 10;
            }
            num = tmp;
        }
        return num;
    }

    public static void main(String[] args) {
        int num = 38;
        System.out.println(addDigits(num));
    }
}
