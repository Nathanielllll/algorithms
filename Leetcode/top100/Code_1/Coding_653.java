package top100.Code_1;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 */
public class Coding_653 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        List<Integer> list = new ArrayList<>();

        public boolean findTarget(TreeNode root, int k) {
            inOrder(root);
            int left = 0;
            int right = list.size() - 1;
            while (left != right) {
                int sum = list.get(left) + list.get(right);
                if (sum > k) {
                    while (left != right && list.get(right) == list.get(--right)) ;
                } else if (sum < k) {
                    while (left != right && list.get(left) == list.get(++left)) ;
                } else {
                    return true;
                }
            }
            return false;
        }

        public void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            list.add(root.val);
            inOrder(root.right);
        }
    }
}
