import java.util.List;
import java.util.Stack;

public class LeetCode_Draft {
//    public class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//
//        TreeNode() {
//        }
//
//        TreeNode(int val) {
//            this.val = val;
//        }
//
//        TreeNode(int val, TreeNode left, TreeNode right) {
//            this.val = val;
//            this.left = left;
//            this.right = right;
//        }
//    }

    public static void main(String[] args) {
        int[] heights = {1};
        System.out.println(largestRectangleArea(heights));
    }

    public static int largestRectangleArea(int[] heights) {
        int result = 0;
        // 找两边比它小的数字
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int curHeight = heights[stack.pop()];
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int rightIndex = i;
                result = Math.max(result, curHeight * (rightIndex - leftIndex - 1));
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int curHeight = heights[stack.pop()];
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            int rightIndex = heights.length;
            result = Math.max(result, curHeight * (rightIndex - leftIndex - 1));
        }
        return result;
    }


}
