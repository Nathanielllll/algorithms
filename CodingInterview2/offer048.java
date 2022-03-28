import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class offer048 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "null,";
            }
            String str = root.val + ",";
            str += serialize(root.left);
            str += serialize(root.right);
            return str;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] strings = data.split("\\,");
            Queue<String> queue = new LinkedList<>(Arrays.asList(strings));
            return process(queue);
        }

        private TreeNode process(Queue<String> queue) {
            String val = queue.poll();
            if ("null".equals(val)) {
                return null;
            }

            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = process(queue);
            node.right = process(queue);
            return node;
        }
    }
}
