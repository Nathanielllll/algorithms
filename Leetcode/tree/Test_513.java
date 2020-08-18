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
