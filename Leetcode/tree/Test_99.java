package tree;

import java.util.Queue;
import java.util.Stack;

/*
二叉搜索树中的两个节点被错误地交换。

请在不改变其结构的情况下，恢复这棵树。

示例1:

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

这里我们有个【规律】发现这两个节点：（记住即可了）
第一个节点，是第一个按照中序遍历时候前一个节点大于后一个节点，我们选取前一个节点，这里指节点 4；

第二个节点，是在第一个节点找到之后，后面出现前一个节点大于后一个节点，我们选择后一个节点，这里指节点 1；

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
            } else if (!stack.isEmpty()) {
                root = stack.pop();

                if (firstNode == null && preNode.val > root.val) {
                    firstNode = preNode;
                }
                if (firstNode != null && preNode.val > root.val) {
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
