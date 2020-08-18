package tree;

import java.util.HashMap;
import java.util.Map;

public class Test_337 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static class Info{
        int headYes;
        int headNo;

        public Info(int headYes, int headNo) {
            this.headYes = headYes;
            this.headNo = headNo;
        }
    }

    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = process(root);
        return Math.max(info.headYes, info.headNo);
    }

    private static Info process(TreeNode root){
        if (root.left == null && root.right == null) {
            return new Info(root.val, 0);
        }
        int headNo = 0;
        int headYes = root.val;

        if (root.left != null) {
            Info left_info = process(root.left);
            headYes += left_info.headNo;
            headNo += Math.max(left_info.headNo, left_info.headYes);
        }
        if (root.right != null) {
            Info right_info = process(root.right);
            headYes += right_info.headNo;
            headNo += Math.max(right_info.headNo, right_info.headYes);
        }

        return new Info(headYes, headNo);
    }
}
