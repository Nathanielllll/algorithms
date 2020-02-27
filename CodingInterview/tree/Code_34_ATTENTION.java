package tree; /**
 * 二叉树中和为某一值的路径
 *
 * DFS在数当中的应用，即前序遍历
 *
 * 典型的回溯法的做法：
 * curSum += root.value;
 * result.add(root.value);
 *
 * 。。。
 *
 * result.remove(result.size() - 1);
 * curSum -= root.value;
 */

import java.util.ArrayList;
import java.util.List;

public class Code_34_ATTENTION {
    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public static void findPath(BinaryTreeNode root, int target) {
        if (root == null) {
            return;
        }
        List<Integer> result = new ArrayList<>();
        findPath(root, 0, target, result);
    }

    private static void findPath(BinaryTreeNode root, int curSum, int target, List<Integer> result) {
        if (root != null) {
            curSum += root.value;
            result.add(root.value);

            if (curSum < target) {
                findPath(root.left, curSum, target, result);
                findPath(root.right, curSum, target, result);
            } else if (curSum == target && root.left == null && root.right == null) {
                System.out.println(result);
            }

            /**典型的回溯法的操作*/
            // 无论是否curSum <=> target，都要进行下面的步骤
            // 移除当前结点
            result.remove(result.size() - 1);
            curSum -= root.value;
        }
    }

    public static void main(String[] args) {
        //          10
        //         /  \
        //        5    12
        //       /\
        //      4  7
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 10;
        root.left = new BinaryTreeNode();
        root.left.value = 5;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 4;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 7;
        root.right = new BinaryTreeNode();
        root.right.value = 12;

        // 有两条路径上的结点和为22
        System.out.println("findPath(root, 22);");
        findPath(root, 22);

        // 没有路径上的结点和为15
        System.out.println("findPath(root, 15);");
        findPath(root, 15);

        // 有一条路径上的结点和为19
        System.out.println("findPath(root, 19);");
        findPath(root, 19);


        //               5
        //              /
        //             4
        //            /
        //           3
        //          /
        //         2
        //        /
        //       1
        BinaryTreeNode root2 = new BinaryTreeNode();
        root2.value = 5;
        root2.left = new BinaryTreeNode();
        root2.left.value = 4;
        root2.left.left = new BinaryTreeNode();
        root2.left.left.value = 3;
        root2.left.left.left = new BinaryTreeNode();
        root2.left.left.left.value = 2;
        root2.left.left.left.left = new BinaryTreeNode();
        root2.left.left.left.left.value = 1;

        // 有一条路径上面的结点和为15
        System.out.println("findPath(root2, 15);");
        findPath(root2, 15);

        // 没有路径上面的结点和为16
        System.out.println("findPath(root2, 16);");
        findPath(root2, 16);

        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        //        \
        //         5
        BinaryTreeNode root3 = new BinaryTreeNode();
        root3.value = 1;
        root3.right = new BinaryTreeNode();
        root3.right.value = 2;
        root3.right.right = new BinaryTreeNode();
        root3.right.right.value = 3;
        root3.right.right.right = new BinaryTreeNode();
        root3.right.right.right.value = 4;
        root3.right.right.right.right = new BinaryTreeNode();
        root3.right.right.right.right.value = 5;

        // 有一条路径上面的结点和为15
        System.out.println("findPath(root3, 15);");
        findPath(root3, 15);

        // 没有路径上面的结点和为16
        System.out.println("findPath(root3, 16);");
        findPath(root3, 16);

        // 树中只有1个结点
        BinaryTreeNode root4 = new BinaryTreeNode();

        root4.value = 1;
        // 有一条路径上面的结点和为1
        System.out.println("findPath(root4, 1);");
        findPath(root4, 1);

        // 没有路径上面的结点和为2
        System.out.println("findPath(root4, 2);");
        findPath(root4, 2);

        // 树中没有结点
        System.out.println("findPath(null, 0);");
        findPath(null, 0);
    }

}
