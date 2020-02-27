package backtracking;

import java.lang.reflect.Array;
import java.util.*;

public class Draft {
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
        int curSum = 0;
        subProcess(root, sum, curSum, stack);
        return result;
    }

    public void subProcess(TreeNode root, int sum, int curSum, Stack<Integer> stack){
        if(root==null){
            return;
        }

        if(curSum == sum && root.left==null && root.right==null) {
            result.add(new LinkedList(stack));
        }


        stack.push(root.val);
        curSum += root.val;

        subProcess(root.left, sum, curSum, stack);
        subProcess(root.right, sum, curSum, stack);

        curSum -= root.val;
        stack.pop();
    }
}
