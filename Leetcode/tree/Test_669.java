package tree;
/*
给定一个二叉搜索树，同时给定最小边界L和最大边界R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。

示例 1:

输入:
    1
   / \
  0   2

  L = 1
  R = 2

输出:
    1
      \
       2
示例 2:

输入:
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

输出:
      3
     /
   2
  /
 1

 */
/*
如果根结点太小，根结点的左子树的所有结点只会更小，说明根结点及其左子树都应该剪掉，因此直接返回右子树的修剪结果。
如果根结点太大，根结点的右子树的所有结点只会更大，说明根结点及其右子树都应该剪掉，因此直接返回左子树的修剪结果。
如果根结点没问题，则递归地修剪左子结点和右子结点。
如果结点为空，说明无需修剪，直接返回空即可。
左右子结点都修剪完后，返回自身。
 */
public class Test_669 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) return null;
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        }else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
}
