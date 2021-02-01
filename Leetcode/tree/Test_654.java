package tree;

public class Test_654 {


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


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int indexOfMaxNum = findIndexOfMaxNum(nums, start, end);

        TreeNode root = new TreeNode(nums[indexOfMaxNum]);
        root.left = helper(nums, start, indexOfMaxNum - 1);
        root.right = helper(nums, indexOfMaxNum + 1, end);
        return root;
    }

    private int findIndexOfMaxNum(int[] nums, int start, int end) {
        int index = start;
        int maxNum = nums[start];
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > maxNum) {
                index = i;
                maxNum = nums[i];
            }
        }
        return index;
    }
}
