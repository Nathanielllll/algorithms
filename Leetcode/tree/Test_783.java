package tree;

public class Test_783 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode preNode;
    int minGap;

    public int minDiffInBST(TreeNode root) {
        preNode = null;
        minGap = Integer.MAX_VALUE;
        helper(root);
        return minGap;
    }

    private void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);

        if (preNode != null) {
            minGap = Math.min(minGap, root.val - preNode.val);
        }

        preNode = root;
        helper(root.right);
    }
}
