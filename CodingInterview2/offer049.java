public class offer049 {
    public class TreeNode {
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

    int sum;
    public int sumNumbers(TreeNode root) {
        sum = 0;
        process(root, 0);
        return sum;
    }

    private void process(TreeNode root, int curNum) {
        if (root == null) {
            return;
        }
        curNum = curNum * 10 + root.val;
        if (root.left == null && root.right == null) {
            sum += curNum;
        } else {
            process(root.left, curNum);
            process(root.right, curNum);
        }
    }
}
