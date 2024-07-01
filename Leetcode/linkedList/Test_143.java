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
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // 旋转second
    ListNode second = slow.next;
    slow.next = null;
    ListNode prev = null;
    while (second != null) {
      ListNode tmp = second.next;
      second.next = prev;
      prev = second;
      second = tmp;
    }

    second = prev;
    ListNode first = head;
    while (second != null) {
      ListNode firstNext = first.next;
      ListNode secondNext = second.next;
      first.next = second;
      second.next = firstNext;
      first = firstNext;
      second = secondNext;
    }
  }


}
