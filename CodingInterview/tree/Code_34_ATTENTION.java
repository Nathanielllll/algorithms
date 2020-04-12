package tree; /**
 * 二叉树中和为某一值的路径
 *
 * DFS在数当中的应用，即前序遍历
 *
 * 典型的回溯法的做法：
 * curSum += root.value;
 * result.add(root.value);
 *
 * 。。。
 *
 * result.remove(result.size() - 1);
 * curSum -= root.value;
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Code_34_ATTENTION {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }


    List<List<Integer>> result;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        result = new LinkedList<>();
        if(root==null){
            return result;
        }
        Stack<Integer> stack = new Stack<>();
        subProcess(root, sum, 0, stack);
        return result;
    }

    public void subProcess(TreeNode root, int sum, int curSum, Stack<Integer> stack){
        if(root==null){
            return;
        }

        stack.push(root.val);
        curSum += root.val;
        if(curSum == sum && root.left==null && root.right==null){
            result.add(new LinkedList(stack));
        }
        subProcess(root.left, sum, curSum, stack);
        subProcess(root.right, sum, curSum, stack);


        curSum -= root.val;
        stack.pop();
    }
}
