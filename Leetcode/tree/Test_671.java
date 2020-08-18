package tree;
/*
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。

给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

示例 1:

输入:
    2
   / \
  2   5
     / \
    5   7

输出: 5
说明: 最小的值是 2 ，第二小的值是 5 。
示例 2:

输入:
    2
   / \
  2   2

输出: -1
说明: 最小的值是 2, 但是不存在第二小的值。
 */
public class Test_671 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
    int count = 0;

    public int findSecondMinimumValue(TreeNode root) {
        helper(root);
        return count == 0 ? -1 : second;
    }

    public void helper(TreeNode root) {
        if (root == null)
            return;

        if (root.val < first) {
            second = first;
            first = root.val;
        } else if (root.val <= second && root.val > first) {
            count++;
            second = root.val;
        }
        helper(root.left);
        helper(root.right);
    }
}
