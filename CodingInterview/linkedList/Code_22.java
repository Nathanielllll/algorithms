package linkedList;

/**
 * 链表中倒数第k个节点
 */
public class Code_22 {

    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

        public ListNode() {
        }
    }

    /**
     * 使用双指针的思想
     * 注意代码的鲁棒性
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
//        head为空指针；
//        k大于链表的长度；
//        输入的参数k为0； 鲁棒性也是很重要的~

        ListNode fast = head, slow = head;
        //考虑特殊情况1、3
        if (head == null || k == 0) {
            return null;
        }

        //走了k步
        for (int i = 0; i < k; i++) {
            //考虑特殊情况2
            if (fast == null && i < k) {
                return null;
            }
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }


}
