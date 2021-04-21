package linkedList;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 */
public class Test_24 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if(head==null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode start = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < 2 && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }

            ListNode next = end.next;
            ListNode pre = start;

            start = start.next;
            end.next = null;

            pre.next = reverse(start);
            start.next = next;
            end = start;
        }

        return dummy.next;
    }

    public static ListNode reverse(ListNode node) {
        ListNode next;
        ListNode pre = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public void printLinkedList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Test_24 solution = new Test_24();

        // 1->2->3->4    2->1->4->3
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(3);

        head = solution.swapPairs(head);
        solution.printLinkedList(head);


    }
}
