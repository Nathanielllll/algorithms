public class offer051 {
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

    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return res;
    }

    // 返回经过root的单边分支最大和
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);

        int p1 = root.val;
        int p2 = root.val + left;
        int p3 = root.val + right;
        int cur = Math.max(Math.max(p1, p2), p3);

        //left->root->right 作为路径与已经计算过历史最大值做比较
        int p4 = root.val + left + right;
        res = Math.max(res, Math.max(cur, p4));

        // 少了p4，因为不可能。返回经过root的【单边最大分支】给当前root的父节点计算使用
        return cur;
    }
}
