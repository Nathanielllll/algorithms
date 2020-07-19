package backtracking;

import java.util.*;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Test_46 {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;

        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 使用哈希表检测一个数字是否使用过
        Set<Integer> used = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        backtrack(nums, len, used, stack, res);
        return res;
    }

    private void backtrack(int[] nums, int len, Set<Integer> used, Stack<Integer> stack, List<List<Integer>> res) {
        if (stack.size() == len) {
            res.add(new ArrayList<>(stack));
        }

        for (int i = 0; i < len; i++) {
            if (!used.contains(i)) {
                used.add(i);
                stack.push(nums[i]);

                backtrack(nums, len, used, stack, res);

                stack.pop();
                used.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Test_46 solution = new Test_46();
        int[] nums = {1,1,2};
        System.out.println(solution.permute(nums));
    }


}
