package linkedList;

import java.util.Stack;

/**
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * <p>
 * 
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 进阶:
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例:
 * <p>
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 */
public class Test_445 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //使用stack的方法
    public ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        Stack<Integer> stack_1 = new Stack<>();
        Stack<Integer> stack_2 = new Stack<>();
        Stack<Integer> stack_3 = new Stack<>();

        while (l1 != null) {
            stack_1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack_2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        while (!stack_1.isEmpty() && !stack_2.isEmpty()) {
            int sum = carry + stack_1.pop() + stack_2.pop();
            stack_3.push(sum % 10);
            carry = sum / 10;
        }

        while (!stack_1.isEmpty()) {
            int sum = carry + stack_1.pop();
            stack_3.push(sum % 10);
            carry = sum / 10;
        }
        while (!stack_2.isEmpty()) {
            int sum = carry + stack_2.pop();
            stack_3.push(sum % 10);
            carry = sum / 10;
        }

        if (carry == 1) {
            stack_3.push(1);
        }

        ListNode result = new ListNode(0);
        ListNode temp = result;
        while (!stack_3.isEmpty()) {
            temp.next = new ListNode(stack_3.pop());
            temp = temp.next;
        }

        return result.next;
    }

    //反转ListNode的方法
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reverseL1 = reverseNode(l1);
        ListNode reverseL2 = reverseNode(l2);

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        int carry = 0;
        while (reverseL1 != null && reverseL2 != null) {
            int sum = carry + reverseL1.val + reverseL2.val;
            temp.next = new ListNode(sum % 10);
            carry = sum / 10;

            temp = temp.next;
            reverseL1 = reverseL1.next;
            reverseL2 = reverseL2.next;
        }

        while (reverseL1 != null) {
            int sum = carry + reverseL1.val;
            temp.next = new ListNode(sum % 10);
            carry = sum / 10;

            temp = temp.next;
            reverseL1 = reverseL1.next;
        }

        while (reverseL2 != null) {
            int sum = carry + reverseL2.val;
            temp.next = new ListNode(sum % 10);
            carry = sum / 10;

            temp = temp.next;
            reverseL2 = reverseL2.next;
        }
        if (carry == 1) {
            temp.next = new ListNode(1);
        }
        temp = dummy.next;
        return reverseNode(temp);
    }

    private ListNode reverseNode(ListNode node) {
        ListNode next = null;
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
