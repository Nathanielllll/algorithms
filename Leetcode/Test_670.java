public class Test_670 {

    /*
    给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。

    示例 1 :

    输入: 2736
    输出: 7236
    解释: 交换数字2和数字7。
    示例 2 :

    输入: 9973
    输出: 9973
    解释: 不需要交换。

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/maximum-swap
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



    解题思路：
    1. 记录所有数字最后出现的位置
    2. 从头遍历所有数字，并找比当前大的数字的位置，进行交换
     */

    public static int maximumSwap(int num) {
        int[] lastIdx = new int[10];
        char[] chars = String.valueOf(num).toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            lastIdx[ch - '0'] = i;
        }

        for (int i = 0; i < chars.length; i++) {
            for (int j = 9; j > chars[i] - '0'; j--) {
                if (lastIdx[j] > i) {
                    char tmp = chars[i];
                    chars[i] = chars[lastIdx[j]];
                    chars[lastIdx[j]] = tmp;
                    return Integer.parseInt(String.valueOf(chars));
                }
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
