package tree;

/**
 * 二叉树的深度
 */
public class Code_55 {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = 0;
        if(root.left!=null){
            left = maxDepth(root.left);
        }

        int right = 0;
        if(root.right!=null){
            right = maxDepth(root.right);
        }

        return Math.max(left, right) + 1;
    }
}
