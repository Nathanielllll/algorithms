package tree;

/**
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明:叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 给定二叉树[3,9,20,null,null,15,7],
 */
public class Test_111 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if ((root.left == null) && (root.right == null)) {
            return 1;
        }
        int left = Integer.MAX_VALUE;
        if (root.left != null) {
            left = minDepth(root.left);
        }

        int right = Integer.MAX_VALUE;
        if (root.right != null) {
            right = minDepth(root.right);
        }

        int depth = Math.min(left, right) + 1;
        return depth;
    }
}
