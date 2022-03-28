package tree;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回false 。

 */
public class Test_110 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 方法三 提前阻断法
     */
    public boolean isBalanced(TreeNode root) {
        //-1 即为存在层数相差大于1
        return depth(root) != -1;
    }

    /**
     * 以当前节点为根节点的树的层数
     * 返回-1的话说明 不满足要求不用求了直接 -1 退出
     *
     * @param root
     * @return
     */
    private static int depth(TreeNode root) {
        //当前节点不存在其层数为0
        if (root == null) {
            return 0;
        }

        if ((root.left == null) && (root.right == null)) {
            return 1;
        }
        //获取左节点的层数
        int left = 0;
        if(root.left!=null){
            left = depth(root.left);
        }
        //如果层数为-1直接截断
        if (left == -1) return -1;

        int right = 0;
        if(root.right!=null){
            right = depth(root.right);
        }
        if (right == -1) return -1;

        return Math.abs(left-right)<=1 ? Math.max(left, right)+1 : -1;
    }
}
