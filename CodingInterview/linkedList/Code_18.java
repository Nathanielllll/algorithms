package linkedList;

/**
 * 在O(1)时间内删除链表的节点
 * <p>
 * 可能节点不在当中，要和面试官进行讨论
 * 最后会出现NullPointerException
 */

public class Code_18 {
    public static class Node {
        int value;
        Node next;
    }

    public static Node deleteNode(Node head, Node toBeDeleted) {
        if (head == null) {
            throw new RuntimeException("invalid input");
        }
        if (toBeDeleted == null) {
            return head;
        }

        //要删除的是头节点
        if (toBeDeleted == head) {
            return head.next;
        }

        //要删除的是尾节点
        if (toBeDeleted.next == null) {
            Node cur = head;
            while (cur.next != toBeDeleted) {
                cur = cur.next;
            }
            cur.next = null;
        //一般情况（要删除的节点在中间）
        } else {
            toBeDeleted.value = toBeDeleted.next.value;
            toBeDeleted.next = toBeDeleted.next.next;
        }
        return head;
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

        Node middle = head.next.next.next.next = new Node();
        head.next.next.next.next.value = 5;

        head.next.next.next.next.next = new Node();
        head.next.next.next.next.next.value = 6;

        head.next.next.next.next.next.next = new Node();
        head.next.next.next.next.next.next.value = 7;

        head.next.next.next.next.next.next.next = new Node();
        head.next.next.next.next.next.next.next.value = 8;

        Node last = head.next.next.next.next.next.next.next.next = new Node();
        head.next.next.next.next.next.next.next.next.value = 9;

        head = deleteNode(head, null); // 删除的结点为空
        printList(head);
        Node node = new Node();
        node.value = 12;

        head = deleteNode(head, head); // 删除头结点
        printList(head);
        head = deleteNode(head, last); // 删除尾结点
        printList(head);
        head = deleteNode(head, middle); // 删除中间结点
        printList(head);

        head = deleteNode(head, node); // 删除的结点不在链表中
        printList(head);
    }
}
