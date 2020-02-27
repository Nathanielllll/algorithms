package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *
 * 示例:
 *
 * 输入: 3
 * 输出:
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class Test_95 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generate_trees(1, n);
    }

    public LinkedList<TreeNode> generate_trees(int start, int end) {
        LinkedList<TreeNode> all_trees = new LinkedList<>();
        if (start > end) {
            all_trees.add(null);
            return all_trees;
        }

        //每个数字作为根节点
        for (int i = start; i <= end; i++) {
            LinkedList<TreeNode> left_tree = generate_trees(start, i - 1);
            LinkedList<TreeNode> right_tree = generate_trees(i + 1, end);
            // connect left and right trees to the root i
            for(TreeNode left : left_tree){
                for(TreeNode right : right_tree){
                    TreeNode current_tree = new TreeNode(i);
                    current_tree.left = left;
                    current_tree.right = right;
                    all_trees.add(current_tree);
                }
            }
        }
        return all_trees;
    }


}
