package tree;

/**
 * 平衡二叉树
 */
public class Code_55_2_ATTENTION {
    public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }


    /**
     * 考虑到 getTreeDepth 函数返回的是int值，同时高度不可能为负数，那么如果求高度过程中我们发现了当前不是平衡二叉树，就返回-1。
     * 如果左右子树都不是平衡二叉树，此时都返回了-1，那么再执行下边的代码。
     * if (Math.abs(leftDepth - rightDepth) > 1) {
     *     return -1;
     * }
     * 它们的差会是 0，不会进入if中，但是本来应该进入 if 返回 -1 的。
     * 所以当发现 leftDepth返回 -1 的时候，我们需要提前返回 -1。rightDepth也会有同样的问题，所以也需要提前返回 -1。
     */

    /**
     * 以当前节点为根节点的树的层数
     * 返回-1的话说明 不满足要求不用求了直接 -1 退出
     *
     * @param root
     * @return
     */
    //https://leetcode-cn.com/problems/balanced-binary-tree/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-25/
    

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
