package linkedList;

/**
 * 合并两个排序的链表
 */
public class Code_25 {
    public static class Node {
        int value;
        Node next;
    }

    public static Node merge(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Node mergeHead = new Node();
        Node cur = mergeHead;
        while (head1 != null && head2 != null) {
            if (head1.value <= head2.value) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }

        if (head1 != null) {
            cur.next = head1;
        }
        if (head2 != null) {
            cur.next = head2;
        }

        /**
         * 稍微主要的点。
         * Node mergeHead = new Node();
         * Node cur = mergeHead;
         *
         * 最后是
         * return mergeHead.next;
         */
        return mergeHead.next;
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


        Node head2 = new Node();
        head2.value = 1;

        head2.next = new Node();
        head2.next.value = 3;

        head2.next.next = new Node();
        head2.next.next.value = 5;

        head2.next.next.next = new Node();
        head2.next.next.next.value = 6;

        head2.next.next.next.next = new Node();
        head2.next.next.next.next.value = 7;

        head = merge(head, head2);
//        head = merge2(head, head2);
        printList(head);
    }
}
