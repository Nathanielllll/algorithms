package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 */
public class Code_37 {
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(int value) {
            this.value = value;
        }

        public BinaryTreeNode() {
        }
    }

    //序列化从根节点开始，因为要使用前序遍历
    public static String serialize(BinaryTreeNode head) {
        if (head == null) {
            return "$,";
        }
        String res = head.value + ",";
        res += serialize(head.left);
        res += serialize(head.right);
        return res;
    }

    //反序列化，在根节点的数值读出来的时候就开始了
    public static BinaryTreeNode reconByPreString(String string) {
        if (string == null) {
            return null;
        }
        String[] values = string.split(",");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    public static BinaryTreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("$")) {
            return null;
        }
        BinaryTreeNode head = new BinaryTreeNode(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    //            10
    //         /      \
    //        6        14
    //       /\        /\
    //      4  8     12  16
    private static void test01() {
        BinaryTreeNode node10 = new BinaryTreeNode();
        node10.value = 10;

        BinaryTreeNode node6 = new BinaryTreeNode();
        node6.value = 6;

        BinaryTreeNode node14 = new BinaryTreeNode();
        node14.value = 14;

        BinaryTreeNode node4 = new BinaryTreeNode();
        node4.value = 4;

        BinaryTreeNode node8 = new BinaryTreeNode();
        node8.value = 8;

        BinaryTreeNode node12 = new BinaryTreeNode();
        node12.value = 12;

        BinaryTreeNode node16 = new BinaryTreeNode();
        node16.value = 16;

        node10.left = node6;
        node10.right = node14;

        node6.left = node4;
        node6.right = node8;

        node14.left = node12;
        node14.right = node16;

        System.out.print("serialize: ");
        String serialize = serialize(node10);
        System.out.println(serialize);

        System.out.print("reconByPreString: ");
        BinaryTreeNode node = reconByPreString(serialize);
        printTree(node);
        System.out.println("null");
    }

    private static void printTree(BinaryTreeNode root) {
        if (root != null) {
            System.out.print(root.value + "->");
            printTree(root.left);
            printTree(root.right);
        }
    }

    public static void main(String[] args) {
        test01();
    }
}
