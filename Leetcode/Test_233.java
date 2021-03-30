/**
 * @author luzhi
 * @date 2021/3/30
 */
public class Test_233 {
    /*
    给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。

    示例 1：

    输入：n = 13
    输出：6
    示例 2：

    输入：n = 0
    输出：0

    low + cur + high组成。如n=21034
    cur == 0，low == 34, high = 21. count += high * 100
    cur == 1, low == 34, high = 2   count += high * 1000 + (low + 1)
    cur == 其他, 如cur = 3, low == 4, high = 210 count += (high + 1) * 10
     */
    public static int countDigitOne(int n) {
        int count = 0;
        long i = 1;
        while (n / i != 0) {
            // 比如 i == 100;
            long high = n / (i * 10);
            long cur = (n / i) % 10;
            long low = n % i;
            if (cur == 0) {
                count += high * i;
            } else if (cur == 1) {
                count += high * i + (low + 1);
            }else {
                count += (high + 1) * i;
            }

            i *= 10;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countDigitOne(13));
    }
}
