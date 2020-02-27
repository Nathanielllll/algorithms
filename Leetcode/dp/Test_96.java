package dp;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 假设n个节点存在二叉排序树的个数是G(n)，令f(i)为以i为根的二叉搜索树的个数
 * G(n) = f(1) + f(2) + ... + f(n)
 * 当i为根节点时，其左子树节点个数为i-1个，右子树节点为n-i，则
 * f(i) = G(i-1) * G(n-i)
 * ======>
 * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + ... + G(n-1) * G(0)
 */
public class Test_96 {
    public int numTrees(int length) {
        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int n = 2; n <= length; n++) {
            for (int i = 1; i <= n; i++) {
                int f_i = dp[i - 1] * dp[n - i];
                dp[n] += f_i;
            }
        }
        return dp[length];
    }
}
