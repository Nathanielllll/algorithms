package linkedList;

import java.util.Stack;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 */
public class Test_234 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(true){
            if (fast == null || fast.next == null) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode cur = head;
        Stack<Integer> stack = new Stack<>();
        while (cur != slow) {
            stack.push(cur.val);
            cur = cur.next;
        }
        //长度为奇数
        if (fast != null) {
            while (!stack.isEmpty()) {
                if (stack.pop() != slow.next.val) {
                    return false;
                }
                slow = slow.next;
            }
        }else {
            while (!stack.isEmpty()) {
                if (stack.pop() != slow.val) {
                    return false;
                }
                slow = slow.next;
            }
        }
        return true;
    }

}
