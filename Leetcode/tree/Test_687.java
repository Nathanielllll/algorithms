package tree;
/*
给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。

注意：两个节点之间的路径长度由它们之间的边数表示。

示例 1:

输入:

              5
             / \
            4   5
           / \   \
          1   1   5
输出:

2
示例 2:

输入:

              1
             / \
            4   5
           / \   \
          4   4   5
输出:

2
 */
public class Test_687 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    private int ans;
    private int dfs(TreeNode node){
        if(node==null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int leftPath = node.left!=null && node.val == node.left.val ? left + 1 : 0;
        int rightPath = node.right!=null && node.val == node.right.val ? right + 1 : 0;

        ans = Math.max(ans, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }

    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }
}
