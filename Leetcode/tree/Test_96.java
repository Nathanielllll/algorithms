package tree;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索
 *
 * 假设n个节点，最后结果为G(n)。一共有以i为根节点的二叉树f(1)+f(2)+...+f(n)=G(n)
 * 每一个f(i)左子树有i-1个节点，右子树有n-i个节点：f(i) = G(i-1)*G(n-i)
 * 因此得到：G(n)=G(0)*G(n-1) + G(1)*G(n-2) + ... + G(n-1)*G(0)
 */
public class Test_96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int num = 2; num <= n ; num++) {
            int res = 0;
            for (int i = 0; i < num; i++) {
                res += dp[i] * dp[num - i - 1];
            }
            dp[num] = res;
        }

        return dp[n];

    }
}
