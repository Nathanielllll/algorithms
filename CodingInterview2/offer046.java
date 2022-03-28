import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class offer046 {
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

    // bfs
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    result.add(node.val);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return result;
    }

    // dfs
    int maxDepth;
    List<Integer> result;
    public List<Integer> rightSideView_dfs(TreeNode root) {
        result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        maxDepth = -1;
        traverse(root, 0);
        return result;
    }

    private void traverse(TreeNode root, int curDepth) {
        if (root == null) return;
        if (curDepth > maxDepth) {
            maxDepth = curDepth;
            result.add(root.val);
        }

        ++curDepth;
        traverse(root.right, curDepth);
        traverse(root.left, curDepth);
    }
}
