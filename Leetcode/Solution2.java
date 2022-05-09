import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    static class Decode{
        public String code(TreeNode node){
            if (node == null) {
                return "null,";
            }
            String str = node.value + ",";
            str += code(node.left);
            str += code(node.right);
            return str;
        }

        public TreeNode deCode(String originalString){
            String[] strs = originalString.split(",");
            Queue<String> queue = new LinkedList<>(Arrays.asList(strs));
            return process(queue);
        }

        private TreeNode process(Queue<String> queue){
            if (queue.isEmpty()) {
                return null;
            }

            String curValue = queue.poll();
            if ("null".equals(curValue)) {
                return null;
            }

            int value = Integer.parseInt(curValue);
            TreeNode node = new TreeNode(value);
            node.left = process(queue);
            node.right = process(queue);
            return node;
        }
    }

    public static void main(String[] args) {
        // case1:
        // 1
        //2 3
//        TreeNode node = new TreeNode(1);
//        node.left = new TreeNode(2);
//        node.right = new TreeNode(3);
//
//        Decode decode = new Decode();
//        String str = decode.code(node);
//        System.out.println(str);
//        TreeNode newNode = decode.deCode(str);

        // case2:
        // null
//        Decode decode = new Decode();
//        String str = decode.code(null);
//        System.out.println(str);
//        TreeNode newNode = decode.deCode(str);




        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);

        node.right = new TreeNode(3);
        node.right.right = new TreeNode(6);
        node.right.right.right = new TreeNode(7);

        Decode decode = new Decode();
        String str = decode.code(node);
        System.out.println(str);
        TreeNode newNode = decode.deCode(str);

        System.out.println(newNode);
    }
}
