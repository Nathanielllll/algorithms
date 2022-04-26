package stack.monotonousStack;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class Test_84 {
    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }

    public static int largestRectangleArea(int[] heights) {
        int result = 0;
        // 找两边比它小的数字，因为求的是：以当前index为高，面积最大的情况。
        // 因此要找出左右两边比它小的数字的位置 -> rightIndex - leftIndex - 1，在这个范围内，所有高度都 >= 当前高度
        Stack<Integer> indexStack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!indexStack.isEmpty() && heights[indexStack.peek()] > heights[i]) {
                int curHeight = heights[indexStack.pop()];
                int leftIndex = indexStack.isEmpty() ? -1 : indexStack.peek();
                int rightIndex = i;
                result = Math.max(result, curHeight * (rightIndex - leftIndex - 1));
            }
            indexStack.push(i);
        }

        while (!indexStack.isEmpty()) {
            int curHeight = heights[indexStack.pop()];
            int leftIndex = indexStack.isEmpty() ? -1 : indexStack.peek();
            int rightIndex = heights.length;
            result = Math.max(result, curHeight * (rightIndex - leftIndex - 1));
        }
        return result;
    }


}
