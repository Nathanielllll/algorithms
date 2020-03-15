package linkedList;

/**
 * 链表中环的入口节点
 */
public class Code_23 {
    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node meetingNode(Node head) {
        //成环的起码条件是有三个节点
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node fast = head.next.next;
        Node slow = head.next;

        while (fast != slow) {
            //无环的情况
            if (fast == null || fast.next == null) {
                throw new RuntimeException("无环");
            }

            fast = fast.next.next;
            slow = slow.next;
        }

        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        Node point = head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = point;

        System.out.println(meetingNode(head).value);
    }
}
