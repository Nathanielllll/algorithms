package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求树的最大深度
 */
public class Test_104 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftDepth = root.left == null ? 0 : maxDepth(root.left);
        int rightDepth = root.right == null ? 0 : maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public int maxDepth2(TreeNode root) {
    if (root == null) {
      return 0;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    int level = 0;
    while (!queue.isEmpty()) {
      int queueSize = queue.size();
      for (int i = 0; i < queueSize; i++) {
        TreeNode node = queue.poll();
        if (node.left != null) {
          queue.add(node.left);
        }
        if (node.right != null) {
          queue.add(node.right);
        }
      }
      ++level;
    }
    return level;
  }
}
