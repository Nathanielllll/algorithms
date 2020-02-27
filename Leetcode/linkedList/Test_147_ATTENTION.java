package linkedList;

import java.util.List;

/**
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * <p>
 *  
 * <p>
 * 插入排序算法：
 * <p>
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 */
public class Test_147_ATTENTION {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 定义三个指针 pre, cur, lat
        //pre    cur    lat
        // h  ->  4  ->  2  ->  5  ->  3  ->  null

        // 创建哨兵
        ListNode h = new ListNode(0);
        h.next = head;
        ListNode pre = h;
        ListNode cur = head;
        ListNode lat;

        while (cur != null) {
            lat = cur.next;
            // 只有 cur.next 比 cur 小才需要向前寻找插入点
            if (lat != null && lat.val < cur.val) {
                while (pre.next != null && pre.next.val < lat.val) {
                    pre = pre.next;
                }
                cur.next = lat.next;
                lat.next = pre.next;
                pre.next = lat;
                // 复位
                pre = h;
            } else {
                cur = lat;
            }
        }
        return h.next;
    }

}
