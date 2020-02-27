package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Coding_78 {

    Stack<Integer> stack = new Stack<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        subProcess(nums, 0);
        return result;
    }

    public void subProcess(int[] nums, int start){
        result.add(new ArrayList<>(stack));
        for (int i = start; i < nums.length; i++) {
            stack.push(nums[i]);
            subProcess(nums, i+1);
            stack.pop();
        }
    }



}
