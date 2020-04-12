package tree;

/**
 * 平衡二叉树
 */
public class Code_55_2_ATTENTION {
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }


    /**
     * 考虑到 getTreeDepth 函数返回的是int值，同时高度不可能为负数，那么如果求高度过程中我们发现了当前不是平衡二叉树，就返回-1。
     * 如果左右子树都不是平衡二叉树，此时都返回了-1，那么再执行下边的代码。
     * if (Math.abs(leftDepth - rightDepth) > 1) {
     *     return -1;
     * }
     * 它们的差会是 0，不会进入if中，但是本来应该进入 if 返回 -1 的。
     * 所以当发现 leftDepth返回 -1 的时候，我们需要提前返回 -1。rightDepth也会有同样的问题，所以也需要提前返回 -1。
     */

    /**
     * 以当前节点为根节点的树的层数
     * 返回-1的话说明 不满足要求不用求了直接 -1 退出
     *
     * @param root
     * @return
     */
    //https://leetcode-cn.com/problems/balanced-binary-tree/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-25/
    public boolean isBalanced(BinaryTreeNode root) {
        return getTreeDepth(root) != -1;
    }

    private int getTreeDepth(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int leftDepth = 0;
        if (root.left != null) {
            leftDepth = getTreeDepth(root.left);
        }
        if (leftDepth == -1) {
            return -1;
        }


        int rightDepth = 0;
        if (root.right != null) {
            rightDepth = getTreeDepth(root.right);
        }
        if (rightDepth == -1) {
            return -1;
        }

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }else {
            return Math.max(leftDepth, rightDepth) + 1;
        }

    }


}
