import java.util.Stack;

public class offer039 {
    /*
    给定非负整数数组 heights，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

示例 1:

输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10


题解：得到当前位置下，左右两边 比它矮且最近的位置。这样就能反向得到左右两边 比它高且最远的位置
     */
    public static int largestRectangleArea(int[] heights) {
        int result = 0;
        int length = heights.length;
        // 是递增栈。但是记录的是index
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]){
                int curIndex = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int rightIndex = i;
                result = Math.max(result, heights[curIndex] * (rightIndex - leftIndex - 1));
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

    public static void main(String[] args) {
        int[] heights = {1,1};
        System.out.println(largestRectangleArea(heights));
    }
}
