package tree;

import java.util.Arrays;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意: 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder =[3,9,20,15,7] 中序遍历 inorder = [9,3,15,20,7] 返回如下的二叉树：
 * <p>
 * 3 / \ 9  20 /  \ 15   7
 */
public class Test_105 {

  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0 || inorder.length == 0) {
      return null;
    }
    int val = preorder[0];
    int midIdx = -1;
    for (int i = 0; i < inorder.length; i++) {
      if (inorder[i] == val) {
        midIdx = i;
        break;
      }
    }

    TreeNode root = new TreeNode(val);
    int[] leftPreorder = Arrays.copyOfRange(preorder, 1, midIdx + 1);
    int[] rightPreorder = Arrays.copyOfRange(preorder, midIdx + 1, preorder.length);

    int[] leftInorder = Arrays.copyOfRange(inorder, 0, midIdx);
    int[] rightInorder = Arrays.copyOfRange(inorder, midIdx + 1, inorder.length);
    root.left = buildTree(leftPreorder, leftInorder);
    root.right = buildTree(rightPreorder, rightInorder);
    return root;
  }
}
