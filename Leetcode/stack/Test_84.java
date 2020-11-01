package stack;

import java.util.Stack;

/*
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
求在该柱状图中，能够勾勒出来的矩形的最大面积。

输入: [2,1,5,6,2,3]
输出: 10


求解数组中元素【右边】第一个比它【小】的元素的下标，从前往后，构造单调递增栈；
求解数组中元素【右边】第一个比它【大】的元素的下标，从前往后，构造单调递减栈；
求解数组中元素【左边】第一个比它【小】的元素的下标，从后往前，构造单调递增栈；
求解数组中元素【左边】第一个比它【大】的元素的下标，从后往前，构造单调递减栈。
 */
public class Test_84 {
    // 找两边第一个？于它的值，再进行计算

//    public static int largestRectangleArea(int[] heights) {
//        //元素出栈，意味着，我们已经计算了以它的顶为上边框的最大矩形。
//        Stack<Integer> stack = new Stack<>();
//        stack.push(-1);
//        int maxArea = 0;
//        for (int i = 0; i < heights.length; i++) {
//            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]){
//                int cur = stack.pop();
//                int left = stack.peek();
//                int right = i;
//                maxArea = Math.max(maxArea, (right - left + 1) * heights[cur]);
//            }
//            stack.push(i);
//        }
//        return maxArea;
//    }
}
