import java.util.HashSet;
import java.util.Stack;

public class offer056 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public boolean findTarget (TreeNode root, int k){
        // 创建一个哈希表：存放已经遍历过的节点值
        HashSet<Integer> set = new HashSet<>();
        // 辅助栈：辅助进行中序遍历
        Stack<TreeNode> stack = new Stack<>();
        // 进行中序遍历
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            // 判断哈希表中是否存在 `k - val`
            if(set.contains(k - node.val))
                return true;
            set.add(node.val);

            root = node.right;
        }
        return false;
    }
}
