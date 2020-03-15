package tree;

/**
 * 计算给定二叉树的所有左叶子之和。
 * <p>
 * 示例：
 * <p>
 *     3
 *    / \
 *   9  20
 *   /  \
 * 15   7
 * <p>
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * <p>
 * 和113、257都很类似？甚至是一样的！
 */
public class Test_404 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    int sum;

    public int sumOfLeftLeaves(TreeNode root) {
        sum = 0;
        if (root == null) {
            return sum;
        }
        getSum(root, false);
        return sum;
    }

    private void getSum(TreeNode root, boolean isLeft) {
        if (root == null) {
            return;
        }

        if (isLeft && root.left == null && root.right == null) {
            sum += root.val;
        } else {
            getSum(root.left, true);
            getSum(root.right, false);
        }
    }


}
