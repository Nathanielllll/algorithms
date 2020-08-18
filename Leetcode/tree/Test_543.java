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
    返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

     */

    /*
    两种可能性：
    1）和此节点有关：左右子树的高度和
    2）和此节点无关：max(左子树的最大距离，子右树的最大距离)

    因此最后结果是：===> 到某个点的最大距离：左子树的最大距离、右子树的最大距离、左右子树的高度和，这三者的最大值
     */
    private class Info{
        public int maxDistance;
        public int height;
        public Info(int dis, int h) {
            maxDistance = dis;
            height  = h;
        }
    }

    private Info process(TreeNode node){
        if (node==null){
            return new Info(0, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int p1 = leftInfo.maxDistance;
        int p2 = rightInfo.maxDistance;
        int p3 = leftInfo.height + rightInfo.height;
        int maxDistance = Math.max(p1, Math.max(p2, p3));
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }


    public int diameterOfBinaryTree(TreeNode root) {
        return process(root).maxDistance;
    }
}
