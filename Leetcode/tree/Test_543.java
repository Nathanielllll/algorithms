package tree;

public class Test_543 {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    /*
    给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
    示例 :
    给定二叉树

              1
             / \
            2   3
           / \
          4   5
    返回3, 它的长度是路径 [4,2,1,3] 或者[5,2,1,3]。

     */

    /*
    两种可能性：
    1）和此节点有关：左右子树的高度和
    2）和此节点无关：max(左子树的最大距离，子右树的最大距离)

    因此最后结果是：===> 到某个点的最大距离：左子树的最大距离、右子树的最大距离、左右子树的高度和，这三者的最大值
     */
    class Info{
        int maxDiameter;
        int depth;

        public Info(int maxDiameter, int depth) {
            this.maxDiameter = maxDiameter;
            this.depth = depth;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = helper(root);
        return info.maxDiameter;
    }

    private Info helper(TreeNode root){
        if(root == null) return new Info(0, 0);

        Info leftInfo = helper(root.left);
        Info rightInfo = helper(root.right);

        // 经过root
        int p1 = leftInfo.depth + rightInfo.depth;
        // 不经过root
        int p2 = Math.max(leftInfo.maxDiameter, rightInfo.maxDiameter);
        int maxDiameter = Math.max(p1, p2);

        int depth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
        return new Info(maxDiameter, depth);
    }
}
