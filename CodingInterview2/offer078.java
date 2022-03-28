public class offer078 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }

        int mid = (left + right) >> 1;
        ListNode p1 = merge(lists, left, mid);
        ListNode p2 = merge(lists, mid + 1, right);
        return mergeTwoSortedList(p1, p2);
    }

    private ListNode mergeTwoSortedList(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoSortedList(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoSortedList(list1, list2.next);
            return list2;
        }
    }
}
