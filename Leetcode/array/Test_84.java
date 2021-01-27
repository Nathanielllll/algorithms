package array;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class Test_84 {
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
