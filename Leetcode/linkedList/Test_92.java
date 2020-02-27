package linkedList;

import java.util.List;

/**
 * 转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 */
public class Test_92 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        if (m < 0 || n < 0 || m > n) {
            throw new RuntimeException("invalid input");
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;
        int i = 0;
        while (i < m - 1) {
            i++;
            pre = pre.next;
            end = end.next;
        }

        while (i < n) {
            i++;
            end = end.next;
        }

        ListNode start = pre.next;
        ListNode next = end.next;

        end.next = null;
        pre.next = reverse(start);
        start.next = next;

        return dummy.next;
    }

    public ListNode reverse(ListNode head){
        ListNode next = null;
        ListNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
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
        Test_92 solution = new Test_92();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        head = solution.reverseBetween(head, 2, 6);
        solution.printLinkedList(head);
    }
}
