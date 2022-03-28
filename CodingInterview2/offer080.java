import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class offer080 {
    List<List<Integer>> result;

    public List<List<Integer>> combine(int n, int k) {
        result = new ArrayList<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        result = new ArrayList<>();
        process(nums, 0, new Stack<>(), k);
        return result;
    }

    private void process(int[] nums, int pos, Stack<Integer> stack, int k) {
        if (stack.size() == k) {
            result.add(new ArrayList<>(stack));
        }
        for (int i = pos; i < nums.length; i++) {
            stack.push(nums[i]);
            process(nums, ++pos, stack, k);
            stack.pop();
        }
    }
}
