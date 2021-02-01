package tree;

public class Test_563 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int result;
    public int findTilt(TreeNode root) {
        result = 0;
        dfs(root);
        return result;
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int leftSum = dfs(node.left);
        int rightSum = dfs(node.right);
        result += Math.abs(leftSum - rightSum);
        return leftSum + rightSum + node.val;
    }
}
