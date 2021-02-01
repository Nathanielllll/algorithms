package tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Test_652 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 记录所有子树以及出现的次数
    HashMap<String, Integer> memo = new HashMap<>();
    // 记录重复的子树根节点
    LinkedList<TreeNode> result = new LinkedList<>();

    /* 主函数 */
    List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return result;
    }

    /* 辅助函数 */
    String traverse(TreeNode root) {
        if (root == null) {
            return "null,";
        }
        String ans = root.val + ",";
        ans += traverse(root.left);
        ans += traverse(root.right);

        int freq = memo.getOrDefault(ans, 0);
        freq += 1;
        // 给子树对应的出现次数加一
        memo.put(ans, freq);
        // 多次重复也只会被加入结果集一次
        if (freq == 2) {
            result.add(root);
        }
        return ans;
    }
}
