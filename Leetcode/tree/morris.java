package tree;

/*
记作当前节点为cur。

1. 如果cur无左孩子，cur向右移动（cur=cur.right）
2. 如果cur有左孩子，找到cur左子树上最右的节点，记为mostright
    2.1. 如果mostright的right指针指向空，让其指向cur，cur向左移动（cur=cur.left）（说明第一次经过这个cur节点）
    2.2. 如果mostright的right指针指向cur，让其指向空，cur向右移动（cur=cur.right）（说明第二次经过这个cur节点）

对于没有左子树的节点，morris遍历只会经过一次；对于有左子树的节点，morris遍历会经过两次
对于有左子树的节点，在第一次经过之后、第二次经过之前，会把左子树遍历完

天然的结构缺陷是没有往上指的指针。因此可以从底层的空闲指针入手。morris实际上在高度模拟系统的栈递归顺序
递归的遍历其实只是对系统的栈递归顺序的加工。

对时间复杂度需要进一步讨论。对每一个有左子树的节点，都有一个找左子树右边界的环节。会不会让时间复杂度上升？怎么证明依然是O(N)?
因为全部找左子树右边界的环节的总代价是趋近于O(N)的，因此时间复杂度是O(N)

morris不是万能的，不是啥题目都能解决的

 */
public class morris {
    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void process(Node head) {
        if(head == null) {
            return;
        }
        // 1
        process(head.left);
        // 2
        process(head.right);
        // 3
    }


    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) { // 过流程
            mostRight = cur.left; // mostRight是cur左孩子
            if (mostRight != null) { // 有左子树
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // mostRight变成了cur左子树上，最右的节点
                if (mostRight.right == null) { // 这是第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }



    // 第一次来到这个cur节点的时候，就打印，就是先序遍历了（能经历两次的情况下）
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) { // 过流程
            mostRight = cur.left; // mostRight是cur左孩子
            if (mostRight != null) { // 有左子树
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // mostRight变成了cur左子树上，最右的节点
                if (mostRight.right == null) { // 这是第一次来到cur
                    System.out.println(cur.value);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;
                }
            } else { // 没有左子树的情况
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
    }


    // 第二次来到这个cur节点的时候，就打印，就是先序遍历了（能经历两次的情况下）
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) { // 过流程
            mostRight = cur.left; // mostRight是cur左孩子
            if (mostRight != null) { // 有左子树
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // mostRight变成了cur左子树上，最右的节点
                if (mostRight.right == null) { // 这是第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }




    // 只把处理时机，放在能第二次回到的自己的节点上，而且是只在第二次进行处理
    // 处理：逆序打印左子树右边界
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // 回到自己两次的节点，且第二次回来的时机
                    mostRight.right = null;
                    printEdge(cur.left);// 唯一的处理时机
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    // 以X为头的树，逆序打印这棵树的右边界
    public static void printEdge(Node X) {
        Node tail = reverseEdge(X);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    // 把right指针认为单链表的next指针
    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }



    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        int preValue = Integer.MIN_VALUE;
        while (cur != null) { // 过流程
            mostRight = cur.left; // mostRight是cur左孩子
            if (mostRight != null) { // 有左子树
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // mostRight变成了cur左子树上，最右的节点
                if (mostRight.right == null) { // 这是第一次来到cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur
                    mostRight.right = null;
                }
            }
            if(cur.value <= preValue) {
                return false;
            }
            preValue = cur.value;
            cur = cur.right;
        }
        return true;
    }





    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        printTree(head);
        morrisIn(head);
        morrisPre(head);
        morrisPos(head);
        printTree(head);

    }

}
