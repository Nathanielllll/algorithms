/**
 * @author luzhi
 * @date 2021/3/27
 */
public class Test_202 {
    /*
    编写一个算法来判断一个数 n 是不是快乐数。

    「快乐数」定义为：

    对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
    然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
    如果 可以变为  1，那么这个数就是快乐数。
    如果 n 是快乐数就返回 true ；不是，则返回 false 。

     

    示例 1：

    输入：19
    输出：true
    解释：
    12 + 92 = 82
    82 + 22 = 68
    62 + 82 = 100
    12 + 02 + 02 = 1
     */
    public static boolean isHappy(int n) {
        while (n != 1) {
            // 如果不是快乐数，一定会循环到4，因此只要在n==4的时候返回false即可
            if (n == 4) {
                return false;
            }
            int tmp = 0;
            while (n != 0) {
                int rest = n % 10;
                tmp += rest * rest;
                n = n / 10;
            }
            n = tmp;
        }
        return true;
    }
}
