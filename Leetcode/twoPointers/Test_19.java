package twoPointers;

/**
 * 给定一个链表，删除链表的倒数第n个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 */
public class Test_19 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**哑节点可以有效防止删除第一个节点的情况*/
    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        //先走了n+1步
        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }
        // Move first to the end, maintaining the gap
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
