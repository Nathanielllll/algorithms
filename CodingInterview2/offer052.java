import java.util.Stack;

public class offer052 {
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

    public static TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }

        TreeNode node = root;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmp = null;
        TreeNode pre = null;
        TreeNode result = null;
        boolean first_hit = false;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                tmp = stack.pop();
                if (pre != null) {
                    pre.left = null;
                    pre.right = tmp;
                }
                if (pre != null && !first_hit) {
                    result = pre;
                    first_hit = true;
                }

                node = tmp.right;
                pre = tmp;
            }

        }
        if (pre != null) {
            pre.left = null;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        increasingBST(root);
    }
}
