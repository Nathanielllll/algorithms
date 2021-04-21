package bit;

/**
 * @author luzhi
 * @date 2021/4/19
 */
public class Test_326 {
    /*
    给定一个整数，写一个函数来判断它是否是 3的幂次方。如果是，返回 true ；否则，返回 false 。

    整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x


    示例 1：

    输入：n = 27
    输出：true
    示例 2：

    输入：n = 0
    输出：false


    知道了 n 的限制，我们现在可以推断出 n 的最大值，也就是 3 的幂，是 1162261467。
    因此我们只需要将 3^{19}除以 n。若余数为 0 意味着 n 是 3^{19}的除数，因此是 3 的幂。
     */

    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
