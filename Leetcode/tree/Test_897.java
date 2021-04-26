package tree;

/**
 * @author luzhi
 * @date 2021/4/25
 */
public class Test_897 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode resNode;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;
        inorder(root);
        root = dummyNode.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);

        // 在中序遍历的过程中修改节点指向
        resNode.right = node;
        node.left = null;
        resNode = node;

        inorder(node.right);
    }
}
