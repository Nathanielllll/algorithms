package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Test_449 {
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

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "null,";

        String result = root.val + ",";
        result += serialize(root.left);
        result += serialize(root.right);
        return result;
    }

    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return reconPreOrder(queue);
    }

    public TreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(value));
        root.left = reconPreOrder(queue);
        root.right = reconPreOrder(queue);
        return root;
    }
}
