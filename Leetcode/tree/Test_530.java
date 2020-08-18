package tree;

import linkedList.Test_109;

public class Test_530 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int pre;
    private int ans;
    public int getMinimumDifference(TreeNode root) {
        pre = Integer.MAX_VALUE;
        ans = Integer.MAX_VALUE;

        inOrder(root);
        return ans;
    }

    private void inOrder(TreeNode root){
        if(root == null) return;

        inOrder(root.left);

        if (pre != Integer.MAX_VALUE) {
            int gap = Math.abs(root.val - pre);
            ans = Math.min(ans, gap);
        }

        pre = root.val;
        inOrder(root.right);
    }
}
