package tree;

import java.util.LinkedList;
import java.util.Queue;

public class Test_623 {
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
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        while(!queue.isEmpty()){
            int cnt = queue.size();
            level++;
            if (level == d) {
                for (int i = 0; i < cnt; i++) {
                    TreeNode temp = queue.poll();
                    TreeNode original_left = temp.left;
                    TreeNode original_right = temp.right;
                    temp.left = new TreeNode(v);
                    temp.right = new TreeNode(v);
                    temp.left.left = original_left;
                    temp.right.right = original_right;
                }
                break;
            }else {
                for (int i = 0; i < cnt; i++) {
                    TreeNode temp = queue.poll();
                    if (temp.left != null) {
                        queue.add(temp.left);
                    }
                    if (temp.right != null) {
                        queue.add(temp.right);
                    }
                }
            }
        }
        return root;
    }
}
