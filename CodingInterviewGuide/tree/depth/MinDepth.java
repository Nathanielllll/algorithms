package tree.depth;

public class MinDepth {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int minDepth(Node head){
        if (head == null) {
            return 0;
        }
        return process(head, 1);
    }

    public static int process(Node cur, int level){
        // base case: 发现叶节点，返回它的高度
        if (cur.left == null && cur.right == null) {
            return level;
        }

        int ans = Integer.MAX_VALUE;
        if (cur.left != null) {
            ans = Math.min(process(cur.left, level+1), ans);
        }
        if (cur.right != null) {
            ans = Math.min(process(cur.right, level+1), ans);
        }
        return ans;
    }
}
