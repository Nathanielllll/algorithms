package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 */
public class Test_257 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    List<String> result = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return result;
        }
        Stack<Integer> stack = new Stack();
        process(root, stack);
        return result;
    }

    public void process(TreeNode root, Stack<Integer> stack){
        if (root == null) {
            return;
        }

        stack.push(root.val);
        if (root.left == null && root.right == null) {
            String value = new String();
            for (int i = 0; i < stack.size(); i++) {
                if (i != stack.size() - 1) {
                    value += stack.get(i) + "->";
                }else {
                    value += stack.get(i);
                }
            }
            result.add(value);
        }else {
            process(root.left, stack);
            process(root.right, stack);
        }
        stack.pop();
    }
}
