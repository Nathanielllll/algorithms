package linkedList;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class Test_203 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode cur = dummy;

        while (cur != null) {
            while (cur.next != null && cur.next.val == val) {
                if (cur.next.next != null) {
                    cur.next = cur.next.next;
                }else {
                    cur.next = null;
                }
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
