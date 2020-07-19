package linkedList;
/*
给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1:

给定链表 1->2->3->4, 重新排列为 1->4->2->3.
示例 2:

给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.

 */
public class Test_143 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (true) {
            if (fast == null || fast.next == null) {
                break;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        slow.next = null;
        //4->3
        second = reverse(second);

        mergeList(head, second);

    }

    public static ListNode reverse(ListNode node){
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


    public static void mergeList(ListNode cur1, ListNode cur2){
        ListNode next1 = null;
        ListNode next2 = null;
        while (cur1 != null && cur2 != null) {
            next1 = cur1.next;
            next2 = cur2.next;
            cur1.next = cur2;
            cur2.next = next1;
            cur1 = next1;
            cur2 = next2;
        }
    }


}
