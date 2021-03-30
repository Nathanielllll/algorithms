package tree;

/**
 * @author luzhi
 * @date 2021/3/1
 */

public class Test_993 {
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

    public boolean isCousins(TreeNode root, int x, int y) {
        preOrder(root, x, y, null, 0);
        return x_parent != y_parent && x_depth == y_depth;
    }

    TreeNode x_parent;
    TreeNode y_parent;
    int x_depth;
    int y_depth;
    private void preOrder(TreeNode root, int x, int y, TreeNode parent, int depth){
        if (root == null) {
            return;
        }
        if (root.val == x) {
            x_parent = parent;
            x_depth = depth;
        }
        if (root.val == y) {
            y_parent = parent;
            y_depth = depth;
        }

        preOrder(root.left, x, y, root, depth + 1);
        preOrder(root.right, x, y, root, depth + 1);
    }
}
