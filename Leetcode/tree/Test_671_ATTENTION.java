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
public class Test_671_ATTENTION {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    long ans = Long.MAX_VALUE;

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        // 由题目可知，root.val必然是最小值
        int minval = root.val;
        dfs(root, minval);
        if (ans == Long.MAX_VALUE) return -1;
        return (int) ans;
    }

    private void dfs(TreeNode root, int minval) {
        if (root == null) return;
        if (minval < root.val && root.val < ans)
            ans = root.val;
        dfs(root.left, minval);
        dfs(root.right, minval);
    }
}
