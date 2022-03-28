package linkedList;

/**
 * 在O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 */
public class Test_148 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //merge sort归并排序！
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 如果是fast = head，在fast长度为2的时候，递归后长度始终为2，不能再分，因此导致栈溢出
        ListNode fast = head.next, slow = head;
        while (true) {
            if (fast == null || fast.next == null) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode next = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(next);

        return mergeTwoLists(left, right);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 递归
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
