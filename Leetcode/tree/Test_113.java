package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 */
public class Test_113 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<List<Integer>> result;

    /**
     * 显然要使用回溯法
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack stack = new Stack();
        process(root, sum, stack);
        return result;
    }

    public void process(TreeNode root, int sum, Stack stack) {
        if (root == null) {
            return;
        }

        stack.push(root.val);
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            result.add(new ArrayList<>(stack));
        } else {
            process(root.left, sum, stack);
            process(root.right, sum, stack);
        }

        stack.pop();
    }
}
