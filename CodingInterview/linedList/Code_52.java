package linedList;

/**
 * 两个链表的第一个公共节点
 */
public class Code_52 {
    public static class ListNode {
        int value;
        ListNode next;
    }

    public static ListNode findFirstCommonNode(ListNode head1, ListNode head2) {
        if(head1==null || head2==null){
            return null;
        }
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        while(cur1!=cur2){
            cur1 = cur1==null? head2:cur1.next;
            cur2 = cur2==null? head1:cur2.next;
        }
        return cur1;
    }

    /**
     * 方法二：
     * 先获得两个链表的长度，然后在较长的链表上先走若干步(两链表长度之差)，接着同时在两个链表上遍历，
     * 找到的第一个相同的节点就是他们的第一个公共节点。时间复杂度O(m + n)。
     */

}
