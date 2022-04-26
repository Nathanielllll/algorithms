package dp;

import java.util.Stack;

public class Test_221 {
    // https://leetcode-cn.com/problems/maximal-square/solution/li-jie-san-zhe-qu-zui-xiao-1-by-lzhlyle/
    // 通过上述题解，明白DP的含义：以此为正方形右下角的最大边长
    public int maximalSquare01(char[][] matrix) {
        // base condition
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;

        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;

        // 相当于已经预处理新增第一行、第一列均为0
        int[][] dp = new int[height + 1][width + 1];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == '1') {
                    dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                }
            }
        }
        return maxSide * maxSide;
    }


    // 方法2
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int result = 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] height = new int[cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else if (matrix[i][j] == '0') {
                    height[j] = 0;
                }
            }

            result = Math.max(result, maxArea(height));
        }
        return result;
    }

    private int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] > height[i]) {
                int curIndex = stack.pop();
                int curHeight = height[curIndex];
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int rightIndex = i;
                int min = Math.min(rightIndex - leftIndex - 1, curHeight);
                result = Math.max(result, min * min);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int curIndex = stack.pop();
            int curHeight = height[curIndex];
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            int rightIndex = height.length;
            int min = Math.min(rightIndex - leftIndex - 1, curHeight);
            result = Math.max(result, min * min);
        }
        return result;
    }

}
