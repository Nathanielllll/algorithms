package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树的第k大节点
 * <p>
 * 二叉搜索树的中序遍历是排序递增的
 */
public class Code_54_TODO {
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public static BinaryTreeNode kthNode(BinaryTreeNode node, int k) {
        if (node == null || k < 0) {
            throw new RuntimeException("invalid input");
        }
        List<BinaryTreeNode> result = new ArrayList();
        kthNodeHelper(node, k, result);

        //假设k肯定小于等于node里面数字的个数
        return result.get(k - 1);
    }

//    public static List<BinaryTreeNode> kthNode(BinaryTreeNode node, int k) {
//        if (node == null || k < 0) {
//            throw new RuntimeException("invalid input");
//        }
//        List<BinaryTreeNode> result = new ArrayList();
//        kthNodeHelper(node, k, result);
//
//        return result;
//    }

    public static void kthNodeHelper(BinaryTreeNode node, int k, List<BinaryTreeNode> result) {
        if (node != null) {
            if (node.left != null) {
                kthNodeHelper(node.left, k, result);
            }
            result.add(node);
            if (result.size() == k) {
                return;
            }
            if (node.right != null) {
                kthNodeHelper(node.right, k, result);
            }
        }
    }

    public static void main(String[] args) {
        //       5
        //    /    \
        //   3     7
        //  / \   / \
        // 2   4 6  8
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 5;
        root.left = new BinaryTreeNode();
        root.left.value = 3;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 2;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 4;
        root.right = new BinaryTreeNode();
        root.right.value = 7;
        root.right.left = new BinaryTreeNode();
        root.right.left.value = 6;
        root.right.right = new BinaryTreeNode();
        root.right.right.value = 8;

        System.out.println(kthNode(root, 7).value);

//        List<BinaryTreeNode> result = kthNode(root, 1);
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println(result.remove(i).value);
//        }
    }

}
