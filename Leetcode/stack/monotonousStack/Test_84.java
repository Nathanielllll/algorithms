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
        // 两个stack，分别找左右两边，距离当面位置 最远的 >= 当面高度的位置 -> 分别找左右两边，距离当面位置 最近的 < 当面高度的位置
        int[] rightMostCloseIdx = new int[heights.length];
        int[] leftMostCloseIdx = new int[heights.length];
        for (int i = 0; i < heights.length; i++) {
            leftMostCloseIdx[i] = -1;
            rightMostCloseIdx[i] = heights.length;
        }
        Stack<Integer> stack1 = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack1.isEmpty() && heights[stack1.peek()] > heights[i]) {
                int idx = stack1.pop();
                rightMostCloseIdx[idx] = i;
            }
            stack1.push(i);
        }

        Stack<Integer> stack2 = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            int h = heights[i];
            while (!stack2.isEmpty() && h < heights[stack2.peek()]) {
                int idx = stack2.pop();
                leftMostCloseIdx[idx] = i;
            }
            stack2.push(i);
        }

        int result = 0;
        for (int i = 0; i < heights.length; i++) {
            int h = heights[i];
            result = Math.max(result, h * (rightMostCloseIdx[i] - leftMostCloseIdx[i] - 1));
        }

        return result;
    }


}
