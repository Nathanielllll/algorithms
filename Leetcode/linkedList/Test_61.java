package linkedList;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动k个位置，其中k是非负数。
 *
 * 示例1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步:0->1->2->NULL
 * 向右旋转 4 步:2->0->1->NULL
 *
 */
public class Test_61 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //双指针的方法
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k < 0) {
            return head;
        }
        // 计算链表的长度
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        // 对k进行取余
        k = k % length;
        if (k == 0) {
            return head;
        }

        // 如果 k != length - 1
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        ListNode temp = p2.next;
        p2.next = null;
        p1.next = head;

        return temp;
    }

    public void printLinkedList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Test_61 solution = new Test_61();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        head = solution.rotateRight(head, 2);
        solution.printLinkedList(head);

    }
}
