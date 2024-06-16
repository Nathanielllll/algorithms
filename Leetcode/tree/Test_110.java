package tree;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * <p>
 * 本题中，一棵高度平衡二叉树定义为：
 * <p>
 * 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3 / \ 9  20 /  \ 15   7 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * 1 / \ 2   2 / \ 3   3 / \ 4   4 返回false 。
 */
public class Test_110 {

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private static class BalancedInfo {

    int depth;
    boolean balanced;

    public BalancedInfo(int depth, boolean balanced) {
      this.depth = depth;
      this.balanced = balanced;
    }
  }

  public boolean isBalanced(TreeNode root) {
    BalancedInfo info = balancedDfs(root);
    return info.balanced;
  }

  private BalancedInfo balancedDfs(TreeNode root) {
    if (root == null) {
      return new BalancedInfo(0, true);
    }
    BalancedInfo leftInfo = balancedDfs(root.left);
    BalancedInfo rightInfo = balancedDfs(root.right);
    int curDepth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
    if (!leftInfo.balanced || !rightInfo.balanced) {
      return new BalancedInfo(curDepth, false);
    }
    boolean curBalanced = Math.abs(leftInfo.depth - rightInfo.depth) <= 1;

    return new BalancedInfo(curDepth, curBalanced);
  }
}
