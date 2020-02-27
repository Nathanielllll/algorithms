import java.util.*;

public class Draft {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] strings = data.split(",");
        Queue<String> queue = new LinkedList<>();
        for (String string : strings) {
            queue.add(string);
        }
        return subProcess(queue);
    }

    public TreeNode subProcess(Queue<String> queue){
        String temp = queue.poll();
        if (temp.equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(temp));
        node.left=subProcess(queue);
        node.right = subProcess(queue);
        return node;
    }
}
