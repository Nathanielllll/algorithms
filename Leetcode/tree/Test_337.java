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

    private class Info{
        int robYes;
        int robNo;

        public Info(int robYes, int robNo) {
            this.robYes = robYes;
            this.robNo = robNo;
        }
    }

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Info info = process(root);
        return Math.max(info.robYes, info.robNo);
    }

    private Info process(TreeNode node){
        if (node == null) {
            return new Info(0, 0);
        }

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int robYes = node.val + leftInfo.robNo + rightInfo.robNo;
        int robNo = Math.max(leftInfo.robNo, leftInfo.robYes) + Math.max(rightInfo.robNo, rightInfo.robYes);

        return new Info(robYes, robNo);
    }
}
