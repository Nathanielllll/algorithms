package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_508 {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    HashMap<Integer, Integer> hashMap;

    public int[] findFrequentTreeSum(TreeNode root) {
        hashMap = new HashMap<>();
        dfs(root);

        int count = 0;
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() < count) {
                continue;
            }

            if (entry.getValue() > count) {
                result.clear();
                count = entry.getValue();
            }
            result.add(entry.getKey());
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int sum = root.val;
        sum += dfs(root.left);
        sum += dfs(root.right);

        hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
        return sum;
    }
}
