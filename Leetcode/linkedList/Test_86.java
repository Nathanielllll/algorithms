package linkedList;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 */
public class Test_86 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode dummy_1 = new ListNode(-1);
        ListNode p1 = dummy_1;

        ListNode dummy_2 = new ListNode(-1);
        ListNode p2 = dummy_2;

        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                p1.next = new ListNode(cur.val);
                p1 = p1.next;
            }else {
                p2.next = new ListNode(cur.val);
                p2 = p2.next;
            }
            cur = cur.next;
        }

        p1.next = dummy_2.next;
//        p2.next = null;
        return dummy_1.next;

    }
}
