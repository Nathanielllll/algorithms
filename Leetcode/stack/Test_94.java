package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 */
public class Test_94 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp;

        while(root!=null || !stack.isEmpty()){
            if(root!=null){
                stack.push(root);
                root = root.left;
            }else if(!stack.isEmpty()) {
                temp = stack.pop();
                list.add(temp.val);
                root = temp.right;
            }
        }
        return list;
    }

}
