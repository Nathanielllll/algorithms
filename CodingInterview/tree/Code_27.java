package tree;

/**
 * 二叉树的镜像
 * <p>
 * 输入一颗二叉树，输出它的镜像
 * 左右节点交换了位置
 *
 * 和Code_32_3（之字形打印）不同
 */
public class Code_27 {
    public static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
    }

    //两棵树的根节点相同，但是它们的左右节点交换了位置
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        swap(root);

        mirrorTree(root.left);
        mirrorTree(root.right);

        return root;
    }

    public static void swap(TreeNode root){
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
