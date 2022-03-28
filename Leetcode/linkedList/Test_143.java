package linkedList;

/*
给定一个单链表L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例1:

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


    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode fast = head.next;
        ListNode slow = head;

        while (true) {
            if (fast == null || fast.next == null) {
                break;
            }

            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode newHead = slow.next;
        slow.next = null;

        newHead = reverseList(newHead);

        while(newHead != null){
            ListNode head_next = head.next;
            head.next = newHead;
            ListNode newHead_next = newHead.next;
            newHead.next = head_next;
            head = head_next;
            newHead = newHead_next;
        }
    }

    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
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

    // 合并两个链表
    static int count = 1;
    public static ListNode mergeList_1(ListNode cur1, ListNode cur2) {
        count++;
        if (cur1 == null) return cur2;
        if (cur2 == null) return cur1;
        if (count % 2 == 0) {
            cur1.next = mergeList_1(cur1.next, cur2);
            return cur1;
        } else {
            cur2.next = mergeList_1(cur1, cur2.next);
            return cur2;
        }
    }


}
