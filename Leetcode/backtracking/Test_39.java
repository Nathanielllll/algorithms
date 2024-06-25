package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * <p>
 * candidates中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括target）都是正整数。 解集不能包含重复的组合。 示例1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7, 所求解集为: [ [7], [2,2,3] ] 示例2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8, 所求解集为: [ [2,2,2,2], [2,3,3], [3,5] ]
 */
public class Test_39 {

  // 方案一：选择法
  public static List<List<Integer>> combinationSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    combinationSumDfs(nums, target, result, stack, 0, 0);
    return result;
  }

  private static void combinationSumDfs(int[] nums, int target, List<List<Integer>> result,
      Stack<Integer> stack, int sum, int idx) {
    if (sum >= target || idx >= nums.length) {
      if (sum == target) {
        result.add(new ArrayList<>(stack));
      }
      return;
    }

    // choose
    stack.add(nums[idx]);
    combinationSumDfs(nums, target, result, stack, sum + nums[idx], idx);
    stack.pop();
    // not choose
    combinationSumDfs(nums, target, result, stack, sum, idx + 1);
  }

  // 方案二：在回溯的过程中记录结点。
  public static List<List<Integer>> combinationSum1(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    combinationSum2Dfs(nums, target, result, stack, 0, 0);
    return result;
  }

  private static void combinationSum2Dfs(int[] nums, int target, List<List<Integer>> result, Stack<Integer> stack, int sum, int start) {
    if (sum == target) {
      result.add(new ArrayList<>(stack));
    }
    for (int i = start; i < nums.length; i++) {
      if (sum <= target) {
        stack.push(nums[i]);
        // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
        combinationSum2Dfs(nums, target, result, stack, sum + nums[i], i);
        stack.pop();
      }
    }
  }
}
