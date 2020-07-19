package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class Test_47 {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;

        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Arrays.sort(nums);
        Stack<Integer> stack = new Stack<>();
        boolean[] used = new boolean[nums.length];
        subProcess(used, stack, nums);
        return result;
    }

    public void subProcess(boolean[] used, Stack<Integer> stack, int[] nums){
        if (stack.size() == nums.length) {
            result.add(new ArrayList(stack));
        }

        for (int i = 0; i < nums.length; i++) {
            if(!used[i]) {
                //十分注意，后面还有个!used[i - 1]
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                stack.push(nums[i]);
                subProcess(used, stack, nums);
                stack.pop();
                used[i] = false;
            }
        }
    }
}
