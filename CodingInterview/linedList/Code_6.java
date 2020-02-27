package linedList; /**
 * 从尾到头打印链表
 */

import java.util.Stack;

public class Code_6 {

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    //反转链表，改变了链表的结构
    public static void reverseList(Node head) {
        Node next = null;
        Node pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        /**最后是以pre为首节点！一定要注意*/
        while (pre != null) {
            System.out.print(pre.value + " ");
            pre = pre.next;
        }
    }

    //不允许改变链表的结构的情况下，可以使用栈
    public static void printListInverselyUsingIteration(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().value + " ");
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(5);

        printListInverselyUsingIteration(root);
        System.out.println();
        reverseList(root);
    }

}
