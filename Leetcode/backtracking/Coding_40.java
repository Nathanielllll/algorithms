package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class Coding_40 {
    List<List<Integer>> result = new ArrayList<>();
    int target;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.target = target;
        if (candidates == null || candidates.length <= 0) {
            return result;
        }
        Arrays.sort(candidates);

        Stack<Integer> stack = new Stack<>();
        findCombinationSum(candidates, 0, 0, stack);
        return result;
    }

    public void findCombinationSum(int[] candidates, int sum, int start, Stack<Integer> stack) {
        if (sum == target) {
            result.add(new ArrayList<>(stack));
        }
        for (int i = start; i < candidates.length && sum + candidates[i] <= target; i++) {
            // 这一步之所以能够生效，其前提是数组一定是排好序的，这样才能保证：
            // 在递归调用的统一深度（层）中，一个元素只使用一次。
            // 这一步剪枝操作基于 candidates 数组是排序数组的前提下
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            stack.push(candidates[i]);
            // 【关键】因为元素不可以重复使用，这里递归传递下去的是 i + 1
            findCombinationSum(candidates, sum + candidates[i], i + 1, stack);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        Coding_40 solution = new Coding_40();
        List<List<Integer>> combinationSum = solution.combinationSum(candidates, target);
        System.out.println(combinationSum);
    }
}
