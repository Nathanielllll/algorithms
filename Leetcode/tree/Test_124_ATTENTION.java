package tree;

/*
给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。



示例 1：

输入：[1,2,3]

       1
      / \
     2   3

输出：6
示例2：

输入：[-10,9,20,null,null,15,7]

  -10
 / \
 9 20
  / \
 15  7

输出：42
 */
public class Test_124_ATTENTION {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return res;
    }

    // 返回经过root的单边分支最大和
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);

        int p1 = root.val;// 只有当前节点
        int p2 = root.val + left; // 当前节点+左边分支最大和
        int p3 = root.val + right;// 当前节点+右边分支最大和
        int cur = Math.max(Math.max(p1, p2), p3);// 这个是最后dfs的结果：经过root的单边分支最大和

        //left->root->right 作为路径与已经计算过历史最大值做比较
        int p4 = root.val + left + right;
        res = Math.max(res, Math.max(cur, p4));

        // 少了p4，因为不可能。返回经过root的【单边最大分支】给当前root的父节点计算使用
        // 因为如果返回p4，可能会出现多条路径乱跑的情况，是不符合题目要求的：只有一条路径。
        return cur;
    }
}
