package tree;

/**
 * 对称的二叉树
 * 用来判断一颗二叉树是不是对称的
 *
 * mirror函数和Code_26的match函数有一定相似之处
 * mirror函数要求镜像相同，而match要求整个相同
 */
public class Code_28 {

    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public static boolean isSymmetric(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }
        return mirror(root.left, root.right);
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

    public static void main(String[] args) {
        //       8
        //    /    \
        //   6     6
        //  / \   / \
        // 5   7 7  5
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 8;
        root.left = new BinaryTreeNode();
        root.left.value = 6;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 5;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 7;
        root.right = new BinaryTreeNode();
        root.right.value = 6;
        root.right.left = new BinaryTreeNode();
        root.right.left.value = 7;
        root.right.right = new BinaryTreeNode();
        root.right.right.value = 5;
        System.out.println(isSymmetric(root));
    }

}
