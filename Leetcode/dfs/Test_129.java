package dfs;

/*
给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

例如，从根到叶子节点路径 1->2->3 代表数字 123。

计算从根到叶子节点生成的所有数字之和。

说明: 叶子节点是指没有子节点的节点。

示例 1:

输入: [1,2,3]
    1
   / \
  2   3
输出: 25
解释:
从根到叶子节点路径 1->2 代表数字 12.
从根到叶子节点路径 1->3 代表数字 13.
因此，数字总和 = 12 + 13 = 25.
 */
public class Test_129 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int result;

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        result = 0;
        dfs(root, 0);
        return result;
    }

    private void dfs(TreeNode node, int current) {
        //加上该节点的val
        current += node.val;
        //判断是否加入到结果中
        if (node.left == null && node.right == null) result += current;
        //左子树
        if (node.left != null) dfs(node.left, current * 10);
        //右子树
        if (node.right != null) dfs(node.right, current * 10);
    }
}
