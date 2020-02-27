/**
 * 两颗二叉树是否对称，以及两颗二叉树是否相同
 */
public class MirrorAndMatch {

    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public static boolean mirror(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root2 == null) {
            return false;
        }

        if (root1.value == root2.value) {
            return mirror(root1.left, root2.right) && mirror(root1.right, root2.left);
        }

        return false;
    }

    public static boolean match(BinaryTreeNode root1, BinaryTreeNode root2) {
        if (root1 == root2) {
            return true;
        }
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }

        if (root1.value == root2.value) {
            return match(root1.left, root2.left) && match(root2.right, root2.right);
        }

        return false;
    }
}
