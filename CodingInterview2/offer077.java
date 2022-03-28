public class offer077 {

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

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return sort(head);
    }

    private ListNode sort(ListNode head) {
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

        ListNode p1 = sort(head);
        ListNode p2 = sort(next);
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

    public static void main(String[] args) {
        offer077 solution = new offer077();
        ListNode node = new ListNode(4);
        node.next = new ListNode(2);
        node.next.next = new ListNode(1);
        node.next.next.next = new ListNode(3);

        solution.sortList(node);

    }
}
