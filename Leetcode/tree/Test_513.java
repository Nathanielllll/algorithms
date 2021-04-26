package tree;

import java.util.LinkedList;
import java.util.Queue;

/*
给定一个二叉树，在树的最后一行找到最左边的值。

示例 1:

输入:

    2
   / \
  1   3

输出:
1
 

示例 2:

输入:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

输出:
7
 */
public class Test_513 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归
     */
    private int result;
    private int maxDepth;
    public int findBottomLeftValue_1(TreeNode root) {
        result = 0;
        maxDepth = -1;
        if(root == null) return 0;

        helper(root, 0);
        return result;
    }

    private void helper(TreeNode root, int curDepth){
        if (root == null) return;

        if (curDepth > maxDepth) {
            result = root.val;
            maxDepth = curDepth;
        }

        curDepth++;
        helper(root.left, curDepth);
        helper(root.right, curDepth);
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                root = queue.poll();
                if(root.right!=null) queue.add(root.right);
                if(root.left!=null) queue.add(root.left);
            }
        }
        return root.val;
    }
}
