package tree;

/**
 * 二叉搜索树的后序遍历序列
 * <p>
 * 1.最后一个数字是树的根节点的值，再分成两个部分。2.第一部分是左子树节点的值，它们都要比根节点小；
 * 3.第二部分是右子树节点的值，它们都要比根节点的值大。
 */
public class Code_33_ATTENTION {
    public static boolean verifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length <= 0) {
            return false;
        }

        return verifySequenceOfBST(sequence, 0, sequence.length - 1);
    }

    public static boolean verifySequenceOfBST(int[] sequence, int start, int end) {
        /**出口，证明是到单个节点了*/
        if (start >= end) {
            return true;
        }

        int index = start;
        int root = sequence[end];

        //找到二叉搜索树右子树的第一个节点
        while (sequence[index] < root && index < end) {
            index++;
        }
        /**下面错误，不能保证前面的左子树也是二叉搜索树*/
//        //条件1，针对特殊情况，可以早点返回，减少递归次数
//        if (index == end) {
//            return true;
//        }

        int right = index;

        //确保right~end-1的数字都是大于root的
        while (sequence[index] > root && index < end) {
            index++;
        }
        //条件2
        if (index != end) {
            return false;
        }

        index = right;
        return verifySequenceOfBST(sequence, start, index - 1) && verifySequenceOfBST(sequence, index, end - 1);
    }

    public static void main(String[] args) {
        //           10
        //         /   \
        //        6     14
        //       /\     /\
        //      4  8  12  16
        int[] data = {4, 8, 6, 12, 16, 14, 10};
        System.out.println("true: " + verifySequenceOfBST(data));

        //           5
        //          / \
        //         4   7
        //            /
        //           6
        int[] data2 = {4, 6, 7, 5};
        System.out.println("true: " + verifySequenceOfBST(data2));

        //               5
        //              /
        //             4
        //            /
        //           3
        //          /
        //         2
        //        /
        //       1
        int[] data3 = {1, 2, 3, 4, 5};
        System.out.println("true: " + verifySequenceOfBST(data3));

        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        //        \
        //         5
        int[] data4 = {5, 4, 3, 2, 1};
        System.out.println("true: " + verifySequenceOfBST(data4));

        // 树中只有1个结点
        int[] data5 = {5};
        System.out.println("true: " + verifySequenceOfBST(data5));

        int[] data6 = {7, 4, 6, 5};
        System.out.println("false: " + verifySequenceOfBST(data6));

        int[] data7 = {4, 6, 12, 8, 16, 14, 10};
        System.out.println("false: " + verifySequenceOfBST(data7));

        int[] data8 = {7, 6, 8, 10, 4, 5};
        System.out.println("false: " + verifySequenceOfBST(data8));

        int[] data9 = {7, 6, 8, 4, 10, 5};
        System.out.println("false: " + verifySequenceOfBST(data9));

        int[] data10 = {6, 7};
        System.out.println("true: " + verifySequenceOfBST(data10));
    }
}
