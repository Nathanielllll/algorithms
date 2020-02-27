package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/***
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 */
public class Coding_90 {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length < 1) {
            return result;
        }
        Arrays.sort(nums);
        Stack<Integer> stack = new Stack<>();
        subProcess(stack, nums, 0);
        return result;
    }

    public void subProcess(Stack<Integer> stack, int[] nums, int start){
        result.add(new ArrayList(stack));
        for (int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }
            stack.push(nums[i]);
            subProcess(stack, nums, i + 1);
            stack.pop();
        }
    }

}
