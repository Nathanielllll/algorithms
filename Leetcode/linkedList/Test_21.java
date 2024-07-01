package linkedList;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4
 */
public class Test_21 {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

  public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;
    while (list1 != null && list2 != null) {
      if (list1.val < list2.val) {
        cur.next = list1;
        list1 = list1.next;
      } else {
        cur.next = list2;
        list2 = list2.next;
      }

      cur = cur.next;
    }
    if (list1 != null) {
      cur.next = list1;
    }
    if (list2 != null) {
      cur.next = list2;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(1);//1,2,4
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(4);

    ListNode l2 = new ListNode(1);//1,3,4
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(4);

  }


}
