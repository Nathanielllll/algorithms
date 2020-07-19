package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历
 */
public class Test_144 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static List<Integer> preorderTraversal(TreeNode node) {
        LinkedList<Integer> list = new LinkedList<>();
        if (node == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp;
        stack.add(node);
        while (!stack.isEmpty()) {
            temp = stack.pop();
            list.add(temp.val);
            if (temp.right != null) {
                stack.add(temp.right);
            }
            if (temp.left != null) {
                stack.add(temp.left);
            }
        }
        return list;
    }
}
