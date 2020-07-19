import java.util.*;

public class Test {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //约定空树不是任意一个树的子结构
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        if (helper(A, B)) {
            return true;
        }else {
            return isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }
    }

    public boolean helper(TreeNode node1, TreeNode node2){
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null) {
            return false;
        }
        if (node2 == null) {
            return true;
        }

        if (node1.val == node2.val) {
            return helper(node1.left, node2.left) && helper(node1.right, node2.right);
        }
        return false;
    }
}
