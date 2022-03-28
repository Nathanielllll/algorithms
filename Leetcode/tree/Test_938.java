package tree;

/**
 * @author luzhi
 * @date 2021/4/27
 */
public class Test_938 {
    /*
    给定二叉搜索树的根结点root，返回值位于范围 [low, high] 之间的所有结点的值的和。

    示例 1：


    输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
    输出：32
    示例 2：


    输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
    输出：23
     */
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int result;
    public int rangeSumBST(TreeNode root, int low, int high) {
        result = 0;
        inOrder(root, low, high);
        return result;
    }

    private void inOrder(TreeNode root, int low, int high) {
        if (root == null) return;
        inOrder(root.left, low, high);
        if (low <= root.val && root.val <= high) {
            result += root.val;
        }
        inOrder(root.right, low, high);
    }
}
