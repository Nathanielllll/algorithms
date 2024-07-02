package tree;

import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。 节点的右子树只包含大于当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树。 示例1:
 * <p>
 * 输入: 2 / \ 1   3 输出: true 示例2:
 * <p>
 * 输入: 5 / \ 1   4 / \ 3   6 输出: false 解释: 输入为: [5,1,4,null,null,3,6]。 根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class Test_98 {

  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  private long preNum;
  private boolean res;

  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (root.left == null && root.right == null) {
      return true;
    }
    preNum = Long.MIN_VALUE;
    res = true;
    isValidBSTDfs(root);
    return res;
  }

  private void isValidBSTDfs(TreeNode root) {
    if (root == null) {
      return;
    }
    isValidBSTDfs(root.left);
    if (root.val <= preNum) {
      res = false;
      return;
    }
    preNum = root.val;
    isValidBSTDfs(root.right);
  }

  public boolean isValidBST_inorder(TreeNode root) {
    if (root == null) {
      return true;
    }
    if (root.left == null && root.right == null) {
      return true;
    }
    long preNum = Long.MIN_VALUE;
    Stack<TreeNode> stack = new Stack<>();
    while (root != null || !stack.isEmpty()) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        TreeNode tmp = stack.pop();
        if (tmp.val <= preNum) {
          return false;
        }
        preNum = tmp.val;
        root = tmp.right;
      }
    }
    return true;
  }
}
