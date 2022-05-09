package tree;

import java.util.*;

public class Test_428 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Codec {
        // Encodes a tree to a single string.
        public String serialize(Node root) {
            if (root == null) return "null,";
            StringBuilder res = new StringBuilder();
            res.append(root.val).append(",").append(root.children.size()).append(",");
            for (Node child : root.children) {
                res.append(serialize(child));
            }
            return res.toString();
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if (data == null) {
                return null;
            }
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
            return dfs(queue);
        }

        private Node dfs(Queue<String> queue) {
            String value = queue.poll();
            if ("null".equals(value)) {
                return null;
            }

            Node root = new Node(Integer.parseInt(value));
            int len = Integer.parseInt(queue.poll());
            List<Node> children = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                children.add(dfs(queue));
            }
            root.children = children;
            return root;
        }
    }
}
