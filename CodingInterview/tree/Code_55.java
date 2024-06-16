package tree;

/**
 * 二叉树的深度
 */
public class Code_55 {

  public static class TreeNode {

    int value;
    TreeNode left;
    TreeNode right;
  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);

    return Math.max(leftDepth, rightDepth) + 1;
  }
}
