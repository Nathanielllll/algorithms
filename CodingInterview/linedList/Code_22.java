package linedList;

/**
 * 链表中倒数第k个节点
 */
public class Code_22 {

    public static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node() {
        }
    }

    /**
     * 使用双指针的思想
     * 注意代码的鲁棒性
     *
     * @param head
     * @param k
     * @return
     */
    public static Node findKthToTail(Node head, int k) {
        if (head == null || k <= 0) {
            throw new RuntimeException("错误的输入");
        }
        //p1先走k-1步，要注意。即1～k-1
        Node p1 = head;
        for (int i = 1; i < k; i++) {
            //需要十分小心，条件是p1.next != null,而不是p1 != null
            if (p1.next != null) {
                p1 = p1.next;
            } else {
                throw new RuntimeException("k超过了链表长度");
            }
        }
        Node p2 = head;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        System.out.println(findKthToTail(head, 6).value);
    }
}
