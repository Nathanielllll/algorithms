package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Test_294 {

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
      String res = root.val + ",";
      res += serialize(root.left);
      res += serialize(root.right);
      return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      String[] spilt = data.split(",");
      Queue<String> queue = new LinkedList<>(Arrays.asList(spilt));
      return deserializeDfs(queue);
    }

    private TreeNode deserializeDfs(Queue<String> queue) {
      if (queue.isEmpty()) {
        return null;
      }
      String string = queue.poll();
      if ("null".equals(string)) {
        return null;
      }
      int val = Integer.parseInt(string);
      TreeNode node = new TreeNode(val);
      node.left = deserializeDfs(queue);
      node.right = deserializeDfs(queue);
      return node;
    }
  }
}
