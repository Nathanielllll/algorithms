import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class offer084 {
    int[] used;
    List<List<Integer>> result;
    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new int[nums.length];
        result = new ArrayList<>();
        if (nums == null || nums.length <= 0) {
            return result;
        }

        Arrays.sort(nums);
        dfs(nums, new Stack<>());
        return result;
    }

    private void dfs(int[] nums, Stack<Integer> stack) {
        if (stack.size() == nums.length) {
            result.add(new ArrayList<>(stack));
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] != 1) {
                continue;
            }

            if (used[i] != 1) {
                used[i] = 1;
                stack.push(nums[i]);
                dfs(nums, stack);
                stack.pop();
                used[i] = 0;
            }
        }
    }
}
