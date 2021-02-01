package tree;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class Test_105 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length || preorder == null || inorder == null) {
            return null;
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private static TreeNode buildTree(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (ps > pe || is > ie) {
            return null;
        }

        int value = preorder[ps];

        //寻找到inorder中value的位置
        int index = is;
        while (index <= ie && inorder[index] != value) {
            index++;
        }
        //????
        if (index > ie) {
            throw new RuntimeException("invalid!");
        }

        //index == 1
        TreeNode node = new TreeNode(value);
        node.left = buildTree(preorder, ps + 1, ps + index - is, inorder, is, index - 1);
        node.right = buildTree(preorder, ps + index - is + 1, pe, inorder, index + 1, ie);

        return node;
    }

    //中序遍历打印二叉树
    public static void printTree(TreeNode head) {
        if (head == null) {
            return;
        }
        printTree(head.left);
        System.out.print(head.val + " ");
        printTree(head.right);
    }

    private static void test7() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
        printTree(root);
    }

    public static void main(String[] args) {
        test7();
    }


}
