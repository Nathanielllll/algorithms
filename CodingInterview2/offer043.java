import java.util.LinkedList;
import java.util.Queue;

public class offer043 {

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

    class CBTInserter {

        TreeNode root;
        Queue<TreeNode> queue;

        public CBTInserter(TreeNode root) {
            this.root = root;
            this.queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()
                    && queue.peek().left != null
                    && queue.peek().right != null) {
                TreeNode node = queue.poll();
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        public int insert(int v) {
            TreeNode node = queue.peek();
            TreeNode addedNode = new TreeNode(v);
            if (node.left == null) {
                node.left = addedNode;
            }else  {
                node.right = addedNode;
                queue.add(node.left);
                queue.add(node.right);
                queue.poll();
            }
            return node.val;
        }

        public TreeNode get_root() {
            return this.root;
        }
    }
}
