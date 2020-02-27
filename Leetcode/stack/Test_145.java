package stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的后序遍历
 * <p>
 * 左、右、根，
 * 反过来就是先根节点，后右子树，再左子树，即可以从前序遍历(根、左、右)转变而来
 * <p>
 * 后序遍历可以变为：先遍历根节点，后遍历右子树，再遍历左子树，的结果的逆序，我们修改先序遍历的代码，
 */
public class Test_145 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp;
        stack.add(root);

        while (!stack.isEmpty()) {
            temp = stack.pop();
            list.addFirst(temp.val);
            if (temp.left != null) {
                stack.add(temp.left);
            }
            if (temp.right != null) {
                stack.add(temp.right);
            }
        }
        return list;
    }
}
