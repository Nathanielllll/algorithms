package tree;

import java.util.Queue;
import java.util.Stack;

/*
二叉搜索树中的两个节点被错误地交换。

请在不改变其结构的情况下，恢复这棵树。

示例 1:

输入: [1,3,null,null,2]

   1
  /
 3
  \
   2

输出: [3,1,null,null,2]

   3
  /
 1
  \
   2
 */
public class Test_99 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode preNode = new TreeNode(Integer.MIN_VALUE);

        TreeNode firstNode = null;
        TreeNode secondNode = null;

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }else if(!stack.isEmpty()){
                root = stack.pop();

                if (firstNode == null && preNode.val > root.val) {
                    firstNode = preNode;
                }
                if(firstNode != null && preNode.val > root.val){
                    secondNode = root;
                }
                preNode = root;
                root = root.right;
            }
        }

        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }
}
