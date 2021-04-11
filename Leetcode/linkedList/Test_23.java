package linkedList;

/**
 * 合并k个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class Test_23 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }

        int mid = (left + right) >> 1;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
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

        // 迭代
//        ListNode dummy = new ListNode(-1);
//        ListNode cur = dummy;
//        while (l1 != null && l2 != null) {
//            if (l1.val < l2.val) {
//                cur.next = new ListNode(l1.val);
//                l1 = l1.next;
//            }else {
//                cur.next = new ListNode(l2.val);
//                l2 = l2.next;
//            }
//            cur = cur.next;
//        }
//
//        if (l1 != null) {
//            cur.next = l1;
//        }
//        if (l2 != null) {
//            cur.next = l2;
//        }
//
//        return dummy.next;
    }

}
