public class offer054 {
    public class TreeNode {
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

    int curSum;
    public TreeNode convertBST(TreeNode root) {
        curSum = 0;
        process(root);
        return root;
    }

    private void process(TreeNode root) {
        if (root == null) return;

        process(root.right);

        curSum += root.val;
        root.val = curSum;

        process(root.left);
    }
}
