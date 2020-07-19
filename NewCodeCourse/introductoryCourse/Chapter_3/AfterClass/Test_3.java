package introductoryCourse.Chapter_3.AfterClass;

/*
使用插入排序对链表进行排序。
Sort a linked list using insertion sort.
 */
public class Test_3 {
    public class ListNode {
        int val;
        ListNode next = null;
    }

    public ListNode insertionSortList (ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return merge(head);
    }

    public ListNode merge(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head.next;
        ListNode slow = head;
        while (true){
            if (fast == null || fast.next == null) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode next = slow.next;
        slow.next = null;
        ListNode left = merge(head);
        ListNode right = merge(next);

        return mergeTwoSortedLists(left, right);
    }

    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2){
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoSortedLists(l1.next, l2);
            return l1;
        }
        l2.next = mergeTwoSortedLists(l1, l2.next);
        return l2;
    }
}
