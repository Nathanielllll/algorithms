package tree;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *
 * 具体思路：
 * （1） 如果当前结点 root 等于NULL，则直接返回NULL
 * （2） 如果 root 等于 p 或者 q ，那这棵树一定返回 p 或者 q
 * （3） 然后递归左右子树，因为是递归，使用函数后可认为左右子树已经算出结果，用 left 和 right 表示
 * （4） 此时若left为空，那最终结果只要看 right；若 right 为空，那最终结果只要看 left
 * （5） 如果 left 和 right 都非空，因为只给了 p 和 q 两个结点，都非空，说明一边一个，因此 root 是他们的最近公共祖先
 * （6） 如果 left 和 right 都为空，则返回空（其实已经包含在前面的情况中了）
 *
 * 时间复杂度是O(n)：每个结点最多遍历一次或用主定理，空间复杂度是O(n)：需要系统栈空间
 */
public class Test_236 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if(root == p || root == q){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        if (left != null && right != null) {
            return root;
        }
        return null;
    }
}
