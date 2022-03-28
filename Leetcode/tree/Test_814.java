package tree;

/**
 * @author luzhi
 * @date 2021/4/29
 */
public class Test_814 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode pruneTree(TreeNode root) {
        if(root == null) return null;
        pruneTree(root.left);
        pruneTree(root.right);
        if(!containsOne(root.left)) root.left = null;
        if(!containsOne(root.right)) root.right = null;

        if (root.left == null && root.right == null && root.val == 0) return null;
        return root;
    }

    private static boolean containsOne(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null && root.val == 0) return false;
        if (root.val == 1) {
            return true;
        }
        return containsOne(root.left) || containsOne(root.right);
    }
}
