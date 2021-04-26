package tree;

import java.util.Stack;

/*
你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。

空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。

示例 1:

输入: 二叉树: [1,2,3,4]
       1
     /   \
    2     3
   /
  4

输出: "1(2(4))(3)"

解释: 原本将是“1(2(4)())(3())”，
在你省略所有不必要的空括号对之后，
它将是“1(2(4))(3)”。
 */
public class Test_606 {
    public static class TreeNode {
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println(tree2str(root));
    }

    static StringBuffer resultBuffer;

    public static String tree2str(TreeNode t) {
        resultBuffer = new StringBuffer();
        dfs(t);
        return resultBuffer.toString();
    }

    private static void dfs(TreeNode root) {
        if (root == null) return;
        resultBuffer.append(root.val);
        if (root.left != null || root.right != null) { // 当其左孩子或右孩子不为空时，不管当前的根节点是否为空都要输出左孩子的值
            // 因为root.left==null的时候，会添加()
            resultBuffer.append("(");
            dfs(root.left);
            resultBuffer.append(")");
            if (root.right != null) { // 只有当右孩子不为空时，才用输出右孩子的值
                resultBuffer.append("(");
                dfs(root.right);
                resultBuffer.append(")");
            }
        }
    }
}
