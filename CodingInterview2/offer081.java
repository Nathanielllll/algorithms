import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class offer081 {
    List<List<Integer>> result;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        result = new ArrayList<>();
        if (candidates == null || candidates.length <= 0) {
            return result;
        }

        Arrays.sort(candidates);

        dfs(candidates, target, new Stack<>(), 0, 0);
        return result;
    }

    private void dfs(int[] candidates, int target, Stack<Integer> stack, int curSum, int pos){
        if(curSum == target){
            result.add(new ArrayList<>(stack));
        }

        for (int i = pos; i < candidates.length && curSum <= target; i++) {
            curSum += candidates[i];
            stack.push(candidates[i]);

            dfs(candidates, target, stack, curSum, i);

            stack.pop();
            curSum -= candidates[i];
        }
    }
}
