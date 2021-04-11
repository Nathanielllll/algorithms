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
    // ==============递归===========
    ListNode reverseBetween1(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween1(head.next, m - 1, n - 1);
        return head;
    }

    ListNode successor = null; // 后驱节点

    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    private ListNode reverse_1(ListNode head){
        if(head.next == null) return head;
        ListNode lastNode = reverse_1(head.next);
        head.next.next = head;
        head.next = null;
        return lastNode;
    }
    
    // ==============迭代===========
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy, p2 = dummy;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }
        ListNode p1 = pre.next;
        for (int i = 0; i < n; i++) {
            p2 = p2.next;
        }
        ListNode next = p2.next;
        p2.next = null;
        pre.next = reverse(p1);
        p1.next = next;

        return dummy.next;
    }

    private ListNode reverse(ListNode node){
        ListNode next = null;
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
