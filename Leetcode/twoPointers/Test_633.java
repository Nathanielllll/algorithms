package twoPointers;
/*
给定一个非负整数c，你要判断是否存在两个整数 a 和 b，使得a^2 + b^2 = c。

示例1:

输入: 5
输出: True
解释: 1 * 1 + 2 * 2 = 5


示例2:

输入: 3
输出: False
 */
public class Test_633 {
    public boolean judgeSquareSum(int c) {
        if (c < 0) return false;

        int i = 0;
        int j = (int) Math.sqrt(c);
        while (i <= j) {
            int sum = i * i + j * j;
            if (sum == c) {
                return true;
            } else if (sum < c) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
