package backtracking;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author luzhi
 * @date 2021/3/1
 */
public class Test_996 {
    public static void main(String[] args) {
        Test_996 test = new Test_996();
        int[] nums = {2, 2, 2};
        System.out.println(test.numSquarefulPerms(nums));
    }

    int result;
    public int numSquarefulPerms(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        result = 0;
        Stack<Integer> stack = new Stack<>();
        boolean[] used = new boolean[nums.length];

        helper(nums, stack, used);
        return result;
    }

    private void helper(int[] nums, Stack<Integer> stack, boolean[] used) {
        if (nums.length == stack.size()) {
            result++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                    continue;
                }

                if (stack.isEmpty() || isSquare(stack.peek(), nums[i])) {
                    stack.push(nums[i]);
                    used[i] = true;
                    helper(nums, stack, used);
                    used[i] = false;
                    stack.pop();
                }
            }
        }
    }

    public boolean isSquare(int a, int b) {
        int tmp = a + b;
        int sq = (int) Math.sqrt(tmp);
        return sq * sq == tmp;
    }
}
