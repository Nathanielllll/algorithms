package tree;

import java.util.Stack;

/**
 * 二叉搜索树与双向链表
 */
public class Code_36_ATTENTION {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public Node treeToDoublyList(Node root) {

        if (root==null) {
            return null;
        }

        Stack<Node> stack = new Stack<>();

        Node pre = null;
        Node head = null;

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else if (!stack.isEmpty()) {
                Node temp = stack.pop();

                /**这里是不同于普通中序遍历的地方*/
                //说明这个是第一个节点，将head设置为当前节点
                if (pre == null) {
                    head = temp;
                //其他节点，将前一个节点的右指针指向当前节点
                }else {
                    pre.right = temp;
                }
                //将当前节点的左指针指向前一个节点
                temp.left = pre;
                //将前节点设置为当前节点
                pre = temp;

                root = temp.right;
            }
        }

        head.left = pre;
        pre.right = head;
        return head;
    }


}
