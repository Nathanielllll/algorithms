import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class offer082 {
    List<List<Integer>> result;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        result = new ArrayList<>();
        if (candidates == null || candidates.length <= 0) {
            return result;
        }

        Arrays.sort(candidates);
        dfs(candidates, target, new Stack<>(), 0, 0);
        return result;
    }

    private void dfs(int[] candidates, int target, Stack<Integer> stack, int curSum, int pos) {
        if (curSum == target) {
            result.add(new ArrayList<>(stack));
        }

        for (int i = pos; i < candidates.length && curSum <= target; i++) {
            if(i > pos && candidates[i] == candidates[i - 1]){
                continue;
            }

            curSum += candidates[i];
            stack.push(candidates[i]);

            dfs(candidates, target, stack, curSum, i + 1);

            stack.pop();
            curSum -= candidates[i];
        }
    }
}
