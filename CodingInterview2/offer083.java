import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class offer083 {

    int[] used;
    List<List<Integer>> result;
    public List<List<Integer>> permute(int[] nums) {
        used = new int[nums.length];
        result = new ArrayList<>();
        if (nums == null || nums.length <= 0) {
            return result;
        }

        dfs(nums, new Stack<>());
        return result;
    }

    private void dfs(int[] nums, Stack<Integer> stack) {
        if (stack.size() == nums.length) {
            result.add(new ArrayList<>(stack));
        }

        for (int i = 0; i < nums.length; i++) {
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
