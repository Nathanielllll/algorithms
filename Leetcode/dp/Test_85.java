package dp;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个仅包含0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例:
 *
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 */
public class Test_85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int[] height = new int[col];
        int[] left = new int[col];
        int[] right = new int[col];
        Arrays.fill(right, col - 1);

        int ans = 0;

        for (int i = 0; i < row; i++) {
            int cur_left = 0;
            int cur_right = col - 1;

            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                }else {
                    height[j] = 0;
                }
            }

            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], cur_left);
                }else {
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }

            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], cur_right);
                }else {
                    right[j] = col - 1;
                    cur_right = j - 1;
                }
            }

            for (int j = 0; j < col; j++) {
                int size = (right[j] - left[j] + 1) * height[j];
                ans = Math.max(ans, size);
            }
        }
        return ans;
    }


    public int maximalRectangle_1(char[][] matrix) {
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
                }else if (matrix[i][j] == '0'){
                    height[j] = 0;
                }
            }

            result = Math.max(result,maxArea(height));
        }
        return result;
    }

    private int maxArea(int[] height){
        if (height == null || height.length == 0) {
            return 0;
        }

        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while(!stack.isEmpty() && height[stack.peek()] > height[i]){
                int curIndex = stack.pop();
                int curHeight = height[curIndex];
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int rightIndex = i;
                result = Math.max(result, (rightIndex - leftIndex - 1) * curHeight);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int curIndex = stack.pop();
            int curHeight = height[curIndex];
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            int rightIndex = height.length;
            result = Math.max(result, (rightIndex - leftIndex - 1) * curHeight);
        }
        return result;
    }

}
