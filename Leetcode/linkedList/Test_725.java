package linkedList;

import graph.Test_1;

import java.util.LinkedList;

public class Test_725 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode() {
        }
    }

    public ListNode[] splitListToParts(ListNode root, int k) {
        int length = 0;
        ListNode cur = root;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        if (length <= k) {
            ListNode[] ans = new ListNode[k];
            cur = root;
            for (int i = 0; i < k && cur!=null; i++) {
                ans[i] = new ListNode(cur.val);
                cur = cur.next;
            }
            return ans;
        }else {
            int res = length % k;
            int size = length / k;

            cur = root;
            ListNode[] ans = new ListNode[k];
            for (int i = 0; i < k; i++) {
                ListNode end = cur;
                int realSize = size;
                if(res > 0){
                    realSize++;
                    res--;
                }

                for (int j = 0; j < realSize - 1; j++) {
                    end = end.next;
                }
                ans[i] = cur;
                cur = end.next;
                end.next = null;
            }
            return ans;
        }
    }

    private ListNode generateListNode(int[] nums){
        ListNode dummy = new ListNode(-1);

        ListNode cur = dummy;
        for (int i = 0; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        Test_725 test = new Test_725();
        ListNode node = test.generateListNode(nums);

        ListNode[] res = test.splitListToParts(node, 3);

    }
}
