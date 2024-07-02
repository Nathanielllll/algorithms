package tree;

import java.util.Arrays;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意: 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder =[9,3,15,20,7] 后序遍历 postorder = [9,15,7,20,3] 返回如下的二叉树：
 * <p>
 * 3 / \ 9  20 /  \ 15   7
 */
public class Test_106 {

  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    if (inorder.length == 0 || postorder.length == 0) {
      return null;
    }
    int len = inorder.length;
    int val = postorder[len - 1];
    int midIdx = -1;
    for (int i = 0; i < inorder.length; i++) {
      if (inorder[i] == val) {
        midIdx = i;
        break;
      }
    }
    int[] leftInorder = Arrays.copyOfRange(inorder, 0, midIdx);
    int[] rightInorder = Arrays.copyOfRange(inorder, midIdx + 1, len);

    int[] leftPostorder = Arrays.copyOfRange(postorder, 0, midIdx);
    int[] rightPostorder = Arrays.copyOfRange(postorder, midIdx, len - 1);
    TreeNode node = new TreeNode(val);
    node.left = buildTree(leftInorder, leftPostorder);
    node.right = buildTree(rightInorder, rightPostorder);
    return node;
  }
}
