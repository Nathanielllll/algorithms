package tree;

public class Test_426 {
    public class Node {
        int val;
        Node left;
        Node right;

        Node(int x) {
            val = x;
        }
    }

    Node firstVisit = null;
    Node preVisit = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        toDoublyList(root);
        firstVisit.left = preVisit;
        preVisit.right = firstVisit;
        return firstVisit;
    }

    public void toDoublyList(Node root) {
        if (root == null) {
            return;
        }
        Node left = root.left, right = root.right;
        treeToDoublyList(left);
        if (firstVisit == null) { // 记录头结点
            firstVisit = root;
        }
        if (preVisit != null) { // 当前节点和上一个节点进行连接
            root.left = preVisit;
            preVisit.right = root;
        }
        preVisit = root; // 记录当前节点
        treeToDoublyList(right);
    }
}
