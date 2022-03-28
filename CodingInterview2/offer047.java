public class offer047 {
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

    /*
    对二叉树进行后序遍历时，对于每一棵子树，都是先遍历它的左子树，然后遍历它的右子树，最后遍历树的根。
如果一棵树需要被整个剪枝掉，那么这棵树的任意一棵子树都属于需要被删除的子树；
找到最小的需要被删除的子树：即值为 0 的叶子节点；当一棵需要删除的树的左右子树均被删除后，其自身也就变为了值为0的叶子节点；
通过上面的分析，利用后序遍历进行解题。解题过程如下：
遍历的同时，为节点的左右节点重新赋值，即删除全为 0 的子树；
如果当前节点是叶子节点，且它的值为 0，返回 null，表示将该节点删除；
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }
}
