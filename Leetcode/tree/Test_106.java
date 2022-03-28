package tree;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder =[9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class Test_106 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length != inorder.length || postorder == null || inorder == null) {
            return null;
        }
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private static TreeNode buildTree(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        if (ps > pe || is > ie) {
            return null;
        }

        // inorder = [9,3,15,20,7]
        //postorder = [9,15,7,20,3]
        int value = postorder[pe];

        //寻找到inorder中value的位置
        int index = is;
        while (index <= ie && inorder[index] != value) {
            index++;
        }
        if (index > ie) {
            throw new RuntimeException("invalid!");
        }

        TreeNode node = new TreeNode(value);
        node.left = buildTree(inorder, is, index - 1, postorder, ps, ps+index-is-1);
        node.right = buildTree(inorder, index + 1, ie, postorder,ps+index-is ,pe-1);

        return node;
    }

    public static void printTree(TreeNode head) {
        if (head == null) {
            return;
        }
        printTree(head.left);
        System.out.print(head.val + " ");
        printTree(head.right);
    }

    private static void test7() {
        int[] postorder = {9,15,7,20,3};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(inorder, postorder);
        printTree(root);
    }

    public static void main(String[] args) {
        test7();
    }

}
