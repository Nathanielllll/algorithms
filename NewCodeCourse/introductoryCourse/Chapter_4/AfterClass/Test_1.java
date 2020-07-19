package introductoryCourse.Chapter_4.AfterClass;

/*
对于一个链表，请设计一个时间复杂度为O(n),额外空间复杂度为O(1)的算法，判断其是否为回文结构。

给定一个链表的头指针A，请返回一个bool值，代表其是否为回文结构。保证链表长度小于等于900。

测试样例：
1->2->2->1
返回：true
 */
public class Test_1 {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public boolean chkPalindrome(ListNode A) {
        if (A == null || A.next == null) {
            return true;
        }

        ListNode fast = A.next;
        ListNode slow = A;
        while (true) {
            if (fast == null || fast.next == null) {
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode p2 = slow.next;
        slow.next = null;
        ListNode p1 = reverseList(A);

        //链表长度为奇数
        if (fast == null) {
            p1 = p1.next;
        }

        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
