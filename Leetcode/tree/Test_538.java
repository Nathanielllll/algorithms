package tree;

/*
给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。

 

例如：

输入: 原始二叉搜索树:
              5
            /   \
           2     13

输出: 转换为累加树:
             18
            /   \
          20     13

 */
public class Test_538 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int pre;
    public TreeNode convertBST(TreeNode root) {
        pre = 0;
        traver(root);
        return root;
    }

    private void traver(TreeNode root) {
        if (root == null) return;

        traver(root.right);

        root.val += pre;
        pre = root.val;

        traver(root.left);
    }
}
