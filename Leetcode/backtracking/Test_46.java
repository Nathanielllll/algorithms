package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3] 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
 */
public class Test_46 {

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    permuteDfs(nums, result, stack);
    return result;
  }

  private void permuteDfs(int[] nums, List<List<Integer>> result, Stack<Integer> stack) {
    if (stack.size() == nums.length) {
      result.add(new ArrayList<>(stack));
      // return 与否不影响最后结果
      return;
    }
    for (int num : nums) {
      if (!stack.contains(num)) {
        stack.push(num);
        permuteDfs(nums, result, stack);
        stack.pop();
      }
    }
  }


}
