package tree;

import java.util.LinkedList;
import java.util.List;

public class Test_501 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private int curCount;
    private int maxCount;
    private int preNum = Integer.MAX_VALUE;
    private List<Integer> ans;

    public int[] findMode(TreeNode root) {
        curCount = 1;
        maxCount = 1;
        ans = new LinkedList<>();
        inOrder(root);
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;

        inOrder(root.left);

        if (preNum == Integer.MAX_VALUE) {
            curCount = maxCount = 1;
            ans.add(root.val);
        } else {
            curCount = root.val == preNum ? curCount + 1 : 1;
            if (curCount == maxCount) {
                ans.add(root.val);
            } else if (curCount > maxCount) {
                ans.clear();
                ans.add(root.val);
                maxCount = curCount;
            }
        }
        preNum = root.val;

        inOrder(root.right);
    }
}
