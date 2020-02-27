package hash;

import java.util.HashMap;

public class Code_35 {
    public static class complexListNode {
        int value;
        complexListNode next;
        complexListNode random;

        public complexListNode(int value) {
            this.value = value;
        }
    }

    /**
     * 方法一 使用HashMap
     *
     * @param head
     * @return
     */
    public static complexListNode cloneNodes(complexListNode head) {
        HashMap<complexListNode, complexListNode> hashMap = new HashMap<>();
        complexListNode cur = head;
        while (cur != null) {
            hashMap.put(cur, new complexListNode(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            hashMap.get(cur).next = hashMap.get(cur.next);
            hashMap.get(cur).random = hashMap.get(cur.random);
            cur = cur.next;
        }
        return hashMap.get(head);
    }

    /**
     * 方法二
     *
     * @param head
     * @return
     */
    public static complexListNode cloneNodes_1(complexListNode head) {
        complexListNode cur = head;
        complexListNode next;
        complexListNode copyCur;
        //对.next的复制
        while (cur != null) {
            next = cur.next;
            copyCur = new complexListNode(cur.value);
            cur.next = copyCur;
            copyCur.next = next;
            cur = next;
        }
        //对.random的复制
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copyCur = cur.next;
            copyCur.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        //对其进行分离
        /**
         * 一定要十分小心 copyCur.next = next != null ? next.next : null;
         */
        cur = head;
        complexListNode res = head.next;
        while (cur != null) {
            next = cur.next.next;
            copyCur = cur.next;
            cur.next = next;
            //特殊情况，next = cur.next.next直接指向null了。
            copyCur.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    public static void printRandLinkedList(complexListNode head) {
        complexListNode cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.random == null ? "- " : cur.random.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        complexListNode head = null;

        head = new complexListNode(1);
        head.next = new complexListNode(2);
        head.next.next = new complexListNode(3);
        head.next.next.next = new complexListNode(4);
        head.next.next.next.next = new complexListNode(5);
        head.next.next.next.next.next = new complexListNode(6);

        head.random = head.next.next.next.next.next; // 1 -> 6
        head.next.random = head.next.next.next.next.next; // 2 -> 6
        head.next.next.random = head.next.next.next.next; // 3 -> 5
        head.next.next.next.random = head.next.next; // 4 -> 3
        head.next.next.next.next.random = null; // 5 -> null
        head.next.next.next.next.next.random = head.next.next.next; // 6 -> 4

        complexListNode res1 = null;
        printRandLinkedList(head);
        res1 = cloneNodes(head);
        printRandLinkedList(res1);
        System.out.println("=========================");

        complexListNode res2 = null;
        res2 = cloneNodes_1(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
