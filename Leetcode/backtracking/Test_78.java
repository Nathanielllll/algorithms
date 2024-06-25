package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一组不含重复元素的整数数组nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3] 输出: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 */
public class Test_78 {

  // 方案一：在回溯的过程中记录结点。
  public List<List<Integer>> subsets(int[] nums) {
    Stack<Integer> stack = new Stack<>();
    List<List<Integer>> result = new ArrayList<>();
    subProcess(nums, 0, result, stack);
    return result;
  }

  public void subProcess(int[] nums, int start, List<List<Integer>> result, Stack<Integer> stack) {
    result.add(new ArrayList<>(stack));
    for (int i = start; i < nums.length; i++) {
      stack.push(nums[i]);
      subProcess(nums, i + 1, result, stack);
      stack.pop();
    }
  }

  // 方案二：选择
  public List<List<Integer>> subsets2(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> subset = new Stack<>();
    subsetsDfs(nums, 0, result, subset);
    return result;
  }

  private void subsetsDfs(int[] nums, int idx, List<List<Integer>> result, Stack<Integer> subset) {
    if (idx >= nums.length) {
      result.add(new ArrayList<>(subset));
      return;
    }

    // 选择当前数字
    subset.add(nums[idx]);
    subsetsDfs(nums, idx + 1, result, subset);
    subset.pop();

    // 不选择当前数字
    subsetsDfs(nums, idx + 1, result, subset);
  }


}
