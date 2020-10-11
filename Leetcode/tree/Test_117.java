package tree;

import java.util.LinkedList;
import java.util.Queue;

public class Test_117 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if(root == null) return null;
        return bfs(root);
    }

    private Node bfs(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                Node cur = queue.poll();
                if (i == cnt - 1) {
                    cur.next = null;
                }else {
                    cur.next = queue.peek();
                }

                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
        }
        return root;
    }
}
