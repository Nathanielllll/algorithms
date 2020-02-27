package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层次遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class Test_103 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack_even = new Stack<>();//偶数行
        Stack<TreeNode> stack_odd = new Stack<>();//奇数行
        TreeNode temp;

        stack_even.add(root);
        boolean reverse = true;

        while (!stack_even.isEmpty() || !stack_odd.isEmpty()) {
            if (reverse) {
                List<Integer> list_even = new ArrayList<>();
                for (int i = stack_even.size() - 1; i >= 0; i--) {
                    temp = stack_even.pop();
                    list_even.add(temp.val);
                    if (temp.left != null) {
                        stack_odd.add(temp.left);
                    }
                    if (temp.right != null) {
                        stack_odd.add(temp.right);
                    }
                }
                list.add(list_even);
            } else {
                List<Integer> list_odd = new ArrayList<>();
                for (int i = stack_odd.size() - 1; i >= 0; i--) {
                    temp = stack_odd.pop();
                    list_odd.add(temp.val);
                    if (temp.right != null) {
                        stack_even.add(temp.right);
                    }
                    if (temp.left != null) {
                        stack_even.add(temp.left);
                    }
                }
                list.add(list_odd);
            }
            reverse = !reverse;
        }
        return list;
    }
}
