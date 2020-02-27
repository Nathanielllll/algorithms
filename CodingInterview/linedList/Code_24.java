package linedList;

/**
 * 反转链表
 */
public class Code_24 {
    public static class Node {
        int value;
        Node next;
    }

    public static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node();
        head.value = 1;

        head.next = new Node();
        head.next.value = 2;

        head.next.next = new Node();
        head.next.next.value = 3;

        head.next.next.next = new Node();
        head.next.next.next.value = 4;

        head.next.next.next.next = new Node();
        head.next.next.next.next.value = 5;

        head.next.next.next.next.next = new Node();
        head.next.next.next.next.next.value = 6;

        head.next.next.next.next.next.next = new Node();
        head.next.next.next.next.next.next.value = 7;

        head.next.next.next.next.next.next.next = new Node();
        head.next.next.next.next.next.next.next.value = 8;

        head.next.next.next.next.next.next.next.next = new Node();
        head.next.next.next.next.next.next.next.next.value = 9;

        printList(head);
        head = reverseList(head);
        printList(head);
    }
}
