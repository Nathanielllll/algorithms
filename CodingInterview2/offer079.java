import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class offer079 {

    List<List<Integer>> result;
    public List<List<Integer>> subsets(int[] nums) {
        result = new ArrayList<>();
        process(nums, 0, new Stack<>());
        return result;
    }

    private void process(int[] nums, int pos, Stack<Integer> stack){
        result.add(new ArrayList<>(stack));

        for (int i = pos; i < nums.length; i++) {
            stack.push(nums[i]);
            process(nums, ++pos, stack);
            stack.pop();
        }
    }
}
