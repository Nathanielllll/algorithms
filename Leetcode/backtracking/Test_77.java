package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入:n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class Test_77 {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        int len = nums.length;

        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Stack<Integer> stack = new Stack<>();
        subProcess(stack, nums, 0, k);
        return result;
    }

    public void subProcess(Stack<Integer> stack, int[] nums, int start, int k) {
        if (stack.size() == k) {
            result.add(new ArrayList(stack));
        }

        for (int i = start; i < nums.length && stack.size() <= k; i++) {
            stack.push(nums[i]);
            subProcess(stack, nums, i + 1, k);
            stack.pop();
        }
    }
}
