package tree;

import java.util.Stack;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例1:
 * <p>
 * 输入:
 *     2
 *    / \
 *  1   3
 * 输出: true
 * 示例2:
 * <p>
 * 输入:
 *       5
 *      / \
 *     1   4
 *       / \
 *      3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *     根节点的值为 5 ，但是其右子节点值为 4 。
 */
public class Test_98 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**思路一，使用递归函数
     * 时间复杂度 : O(N)。每个结点访问一次。
     * 空间复杂度 : O(N)。我们跟进了整棵树。*/
    // 这意味着我们需要在遍历树的同时保留结点的上界与下界，在比较时不仅比较子结点的值，也要与上下界比较。
    public static boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }

        int val = node.val;
        if (lower != null && val <= lower) {
            return false;
        }
        if (upper != null && val >= upper) {
            return false;
        }
        if (!helper(node.left, lower, val)) {
            return false;
        }
        if (!helper(node.right, val, upper)) {
            return false;
        }
        return true;
    }

    public static boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    /**思路二：使用中序遍历的方法*/
    public static boolean isValidBST_2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        long inorder = Long.MIN_VALUE;
        if (root != null) {
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.left;
                }else {
                    root = stack.pop();
                    if (root.val <= inorder) {
                        return false;
                    }
                    inorder = root.val;
                    root = root.right;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
//        root.right = new TreeNode(4);
//        root.right.left = new TreeNode(3);
//        root.right.right = new TreeNode(6);
        System.out.println(isValidBST_2(root));
    }
}
