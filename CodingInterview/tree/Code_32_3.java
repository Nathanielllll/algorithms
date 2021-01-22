package tree;

import java.util.*;

/**
 * 之字形打印二叉树
 */
public class Code_32_3 {
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public List<List<Integer>> levelOrder(BinaryTreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        int level = 0;

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < cnt; i++) {
                BinaryTreeNode curNode = queue.poll();

                // 只需要在这里判断即可，queue的put顺序不变
                if (level % 2 == 0) {
                    list.add(curNode.value);
                }else {
                    list.addFirst(curNode.value);
                }

                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            res.add(list);

            level++;
        }
        return res;
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
