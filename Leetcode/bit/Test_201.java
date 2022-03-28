package bit;
/*
给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。

示例 1:

输入: [5,7]
输出: 4
示例 2:

输入: [0,1]
输出: 0

方法：找m和n，最左的相同的位置。因为右边那些不同的位置，按位与都会变成0
 */
public class Test_201 {
    public int rangeBitwiseAnd(int m, int n) {
        int t = 0;
        while (m != n){
            m = m >> 1;
            n = n >> 1;
            t += 1;
        }
        return m << t;
    }

    public static void main(String[] args) {
        System.out.println(1 | 1);
    }
}
