package tree;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 以及108和109题的练习
 */
public class Test_108 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }

        return toBST(nums, 0, nums.length - 1);
    }

    public TreeNode toBST(int[] nums, int start, int end){
        if (start > end) {
            return null;
        }

        int middle = (start + end) >> 1;
        //前序遍历了，和109不同
        TreeNode node = new TreeNode(nums[middle]);
        node.left = toBST(nums, start, middle - 1);
        node.right = toBST(nums, middle + 1, end);

        return node;
    }
}
