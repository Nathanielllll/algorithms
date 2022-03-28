public class offer050 {
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

    public int pathSum(TreeNode root, int targetSum) {
        if(root == null) return 0;
        return process(root, 0, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    private int process(TreeNode node, int curSum, int targetSum) {
        if (node == null) return 0;

        // 到当前节点的和，满足targetSum
        int ans = 0;
        // 到当前节点的和
        curSum += node.val;
        if (curSum == targetSum) {
            ++ans;
        }

        // 当前节点的ans + 左子节点的ans + 右子节点的ans
        return ans + process(node.left, curSum, targetSum) + process(node.right, curSum, targetSum);
    }
}
