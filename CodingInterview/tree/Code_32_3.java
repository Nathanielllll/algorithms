package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 之字形打印二叉树
 */
public class Code_32_3 {
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public static void printTreeInZ(BinaryTreeNode root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> stack_even = new Stack<>();//偶数行
        Stack<BinaryTreeNode> stack_odd = new Stack<>();//奇数行
        BinaryTreeNode temp;

        stack_even.add(root);
        boolean reverse = true;

        while (!stack_even.isEmpty() || !stack_odd.isEmpty()) {
            if (reverse) {
                for (int i = stack_even.size(); i > 0; i--) {
                    temp = stack_even.pop();
                    System.out.print(temp.value + " ");
                    if (temp.left != null) {
                        stack_odd.add(temp.left);
                    }
                    if (temp.right != null) {
                        stack_odd.add(temp.right);
                    }
                }
            } else {
                for (int i = stack_odd.size(); i > 0; i--) {
                    temp = stack_odd.pop();
                    System.out.print(temp.value + " ");
                    if (temp.right != null) {
                        stack_even.add(temp.right);
                    }
                    if (temp.left != null) {
                        stack_even.add(temp.left);
                    }
                }
            }
            System.out.println();
            reverse = !reverse;
        }


    }

    public static List<List<Integer>> zigzagLevelOrder(BinaryTreeNode root) {
        // if (root == null) {
        //     return ;
        // }

        Stack<BinaryTreeNode> stack_even = new Stack<>();//偶数行
        Stack<BinaryTreeNode> stack_odd = new Stack<>();//奇数行
        BinaryTreeNode temp;

        stack_even.add(root);
        boolean reverse = true;
        List<List<Integer>> list = new LinkedList<>();

        while (!stack_even.isEmpty() || !stack_odd.isEmpty()) {
            if (reverse) {
                List<Integer> list_even = new ArrayList<>();
                for (int i = stack_even.size(); i > 0; i--) {
                    temp = stack_even.pop();
                    if (temp != null) {
                        list_even.add(temp.value);
                    }
                    if (temp.left != null) {
                        stack_odd.add(temp.left);
                    }
                    if (temp.right != null) {
                        stack_odd.add(temp.right);
                    }
                }
                list.add(list_even);
            } else {
                List<Integer> list_odd = new ArrayList<>();
                for (int i = stack_odd.size(); i > 0; i--) {
                    temp = stack_odd.pop();
                    if (temp != null) {
                        list_odd.add(temp.value);
                    }
                    if (temp.right != null) {
                        stack_even.add(temp.right);
                    }
                    if (temp.left != null) {
                        stack_even.add(temp.left);
                    }
                }
                list.add(list_odd);
            }
            reverse = !reverse;
        }
        return list;
    }

    public static void main(String[] args) {

        //       8
        //    /    \
        //   6     10
        //  / \   / \
        // 5   7 9  11
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 8;
        root.left = new BinaryTreeNode();
        root.left.value = 6;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 5;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 7;
        root.right = new BinaryTreeNode();
        root.right.value = 10;
        root.right.left = new BinaryTreeNode();
        root.right.left.value = 9;
        root.right.right = new BinaryTreeNode();
        root.right.right.value = 11;
//        printTreeInZ(root);
        System.out.println(zigzagLevelOrder(root));

    }
}
