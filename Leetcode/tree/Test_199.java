package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:

输入:[1,2,3,null,5,null,4]
输出:[1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

 */
public class Test_199 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // dfs
    int maxDepth;
    List<Integer> result;
    public List<Integer> rightSideView_dfs(TreeNode root) {
        result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        maxDepth = -1;
        traverse(root, 0);
        return result;
    }

    private void traverse(TreeNode root, int curDepth) {
        if (root == null) return;
        if (curDepth > maxDepth) {
            maxDepth = curDepth;
            result.add(root.val);
        }

        ++curDepth;
        traverse(root.right, curDepth);
        traverse(root.left, curDepth);
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            for (int i = queue.size() - 1; i >= 0; i--) {
                TreeNode temp = queue.poll();
                if (i == 0) {
                    res.add(temp.val);
                }

                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
        }
        return res;
    }
}
