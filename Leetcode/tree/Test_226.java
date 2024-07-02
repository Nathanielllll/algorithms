package tree;
/*
翻转一棵二叉树。

示例：

输入：

     4
   /   \
  2     7
 / \   / \
1   3 6   9
输出：

     4
   /   \
  7     2
 / \   / \
9   6 3   1

 */
public class Test_226 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode newNode = new TreeNode(root.val);
    newNode.left = invertTree(root.right);
    newNode.right = invertTree(root.left);
    return newNode;
  }

}
