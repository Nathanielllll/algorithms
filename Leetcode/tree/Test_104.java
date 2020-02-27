package tree;

/**
 * 求树的最大深度
 */
public class Test_104 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = 0;
        if (root.left != null) {
            left = maxDepth(root.left);
        }

        int right = 0;
        if (root.right != null) {
            right = maxDepth(root.right);
        }

        return Math.max(left, right) + 1;
    }
}
