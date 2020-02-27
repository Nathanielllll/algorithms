package tree;

/**
 * 重建二叉树，给出了前序遍历和中序遍历
 */
public class Code_7 {
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }
    }

    public static BinaryTreeNode construct(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length || preorder == null || inorder == null) {
            return null;
        }
        return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }


    public static BinaryTreeNode construct(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (ps > pe || is > ie) {
            return null;
        }
        //root的值
        int value = preorder[ps];
        //index是root的值对应中序遍历的位置
        int index = is;
        while (index <= ie && inorder[index] != value) {
            index++;
        }
        //如果对应位置的顺序不对，也能抛出此错误
        if (index > ie) {
            throw new RuntimeException("错误输入");
        }

        BinaryTreeNode root = new BinaryTreeNode(value);
        root.left = construct(preorder, ps + 1, ps + index - is, inorder, is, index - 1);
        root.right = construct(preorder, ps + index - is + 1, pe, inorder, index + 1, ie);

        return root;
    }

    //中序遍历打印二叉树
    public static void printTree(BinaryTreeNode head) {
        if (head == null) {
            return;
        }
        printTree(head.left);
        System.out.print(head.value + " ");
        printTree(head.right);
    }

// 普通二叉树
    //              1
    //           /     \
    //          2       3
    //         /       / \
    //        4       5   6
    //         \         /
    //          7       8

    private static void test1() {
        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 所有结点都没有右子结点
    //            1
    //           /
    //          2
    //         /
    //        3
    //       /
    //      4
    //     /
    //    5
    private static void test2() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {5, 4, 3, 2, 1};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 所有结点都没有左子结点
    //            1
    //             \
    //              2
    //               \
    //                3
    //                 \
    //                  4
    //                   \
    //                    5
    private static void test3() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {1, 2, 3, 4, 5};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 树中只有一个结点
    private static void test4() {
        int[] preorder = {1};
        int[] inorder = {1};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 完全二叉树
    //              1
    //           /     \
    //          2       3
    //         / \     / \
    //        4   5   6   7
    private static void test5() {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 输入空指针
    private static void test6() {
        construct(null, null);
    }

    // 输入的两个序列不匹配
    private static void test7() {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        BinaryTreeNode root = construct(preorder, inorder);
        printTree(root);
    }


    public static void main(String[] args) {

//        test1();
//        System.out.println();
//        test2();
//        System.out.println();
//        test3();
//        System.out.println();
//        test4();
//        System.out.println();
//        test5();
//        System.out.println();
//        test6();
        System.out.println();
        test7();

    }
}
