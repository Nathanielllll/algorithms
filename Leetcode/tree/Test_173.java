package tree;

import java.util.Stack;

public class Test_173 {

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


    class BSTIterator {

        TreeNode root;
        Stack<TreeNode> stack;
        public BSTIterator(TreeNode root) {
            this.root = root;
            stack = new Stack<>();
        }

        public int next() {
            assert(hasNext());
            int result = -1;

            while (root != null || !stack.isEmpty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                }else {
                    root = stack.pop();
                    result = root.val;
                    root = root.right;
                    break;
                }
            }
            return result;
        }

        public boolean hasNext() {
            return root != null || !stack.isEmpty();
        }
    }
}
