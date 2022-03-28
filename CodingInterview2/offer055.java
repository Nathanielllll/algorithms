import java.util.Stack;

public class offer055 {
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

    static class BSTIterator {

        Stack<TreeNode> stack;
        TreeNode node;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            node = root;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public int next() {
            TreeNode tmp = null;
            while (node != null || !stack.isEmpty()) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    tmp = stack.pop();
                    node = tmp.right;
                    break;
                }
            }
            return tmp == null ? 0 : tmp.val;
        }

        public boolean hasNext() {
            return node != null || !stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator bstIterator = new BSTIterator(root);
    }

}
