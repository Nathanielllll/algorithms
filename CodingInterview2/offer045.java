import java.util.LinkedList;
import java.util.Queue;

public class offer045 {
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
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node = queue.peek();
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
        }
        return node.val;
    }

    // dfs
    int maxDepth;
    int result;
    public int findBottomLeftValue_dfs(TreeNode root) {
        maxDepth = -1;
        result = 0;

        traverse(root, 0);
        return result;
    }

    private void traverse(TreeNode root, int curDepth){
        if(root == null) return;
        if (curDepth > maxDepth) {
            maxDepth = curDepth;
            result = root.val;
        }

        ++curDepth;
        traverse(root.left, curDepth);
        traverse(root.right, curDepth);
    }
}
