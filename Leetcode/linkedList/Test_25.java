package linkedList;

/**
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * <p>
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当k= 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当k= 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class Test_25 {

  public static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  public static ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode left = dummy;
    ListNode right = left;
    while (true) {
      int countDown = k;
      while (right != null && countDown > 0) {
        right = right.next;
        countDown--;
      }
      if (right == null) {
        break;
      }
      ListNode rightNext = right.next;
      ListNode leftNext = left.next;
      right.next = null;
      left.next = reverse(leftNext);
      leftNext.next = rightNext;
      left = leftNext;
      right = left;
    }
    return dummy.next;
  }

  public static ListNode reverse(ListNode node) {
    ListNode next;
    ListNode pre = null;
    while (node != null) {
      next = node.next;
      node.next = pre;
      pre = node;
      node = next;
    }
    return pre;
  }

}
