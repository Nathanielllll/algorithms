package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * 回溯法
 */
public class Test_216 {
    Stack<Integer> stack;
    List<List<Integer>> result;

    public List<List<Integer>> combinationSum3(int k, int n) {
        result = new ArrayList<>();
        stack = new Stack<>();

        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) {
            nums[i] = i + 1;
        }

        backTracking(k, n, 0, 0, nums);
        return result;
    }

    private void backTracking(int k, int n, int index, int sum, int[] nums) {
        if (stack.size() == k && sum == n) {
            List<Integer> subResult = new ArrayList<>(stack);
            result.add(subResult);
        }

        for (int i = index; i < 9 && sum + nums[i] <= n; i++) {
            stack.push(nums[i]);
            // 【关键】因为元素不可以重复使用，这里递归传递下去的是 i + 1
            backTracking(k, n, i + 1,sum + nums[i], nums);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        int k = 3;
        int n = 9;
        Test_216 solution = new Test_216();
        System.out.println(solution.combinationSum3(k, n));
    }
}
