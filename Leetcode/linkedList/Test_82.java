package linkedList;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。
 * <p>
 * 示例1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 */
public class Test_82 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1000);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = dummy.next;

        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }

            if (pre.next == cur) {
                pre = pre.next;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    public void printLinkedList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Test_82 solution = new Test_82();

        // 1->1->1->2->3
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
//        head.next.next.next = new ListNode(2);
//        head.next.next.next.next = new ListNode(3);

        head = solution.deleteDuplicates(head);
        solution.printLinkedList(head);


    }
}
