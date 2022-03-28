import java.util.Arrays;
import java.util.Stack;

public class offer040 {
    /*
    给定一个由0 和 1组成的矩阵 matrix，找出只包含 1 的最大矩形，并返回其面积。

注意：此题 matrix输入格式为一维 01 字符串数组。

示例 1：
输入：matrix = ["10100","10111","11111","10010"]
输出：6
解释：最大矩形如上图所示。
示例 2：

输入：matrix = []
输出：0
示例 3：

输入：matrix = ["0"]
输出：0
示例 4：

输入：matrix = ["1"]
输出：1
示例 5：

输入：matrix = ["00"]
输出：0
     */
    public static int maximalRectangle(String[] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length();

        int[] heights = new int[cols];
        // 从0行到当前行间距下，当前列的左边的为1的最近的位置
        int[] left = new int[cols];
        // 从0行到当前行间距下，当前列的右边的为1的最近的位置
        int[] right = new int[cols];
        Arrays.fill(right, cols - 1);

        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i].charAt(j) == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }

            int leftIndex = 0;
            for (int j = 0; j < cols; j++) {
                if (matrix[i].charAt(j) == '1') {
                    left[j] = Math.max(left[j], leftIndex);
                } else {
                    left[j] = 0;
                    leftIndex = j + 1;
                }
            }

            int rightIndex = cols - 1;
            for (int j = cols - 1; j >= 0; j--) {
                if (matrix[i].charAt(j) == '1') {
                    right[j] = Math.min(right[j], rightIndex);
                } else {
                    right[j] = cols - 1;
                    rightIndex = j - 1;
                }
            }

            for (int j = 0; j < cols; j++) {
                result = Math.max(result, heights[j] * (right[j] - left[j] + 1));
            }
        }
        return result;
    }

    public static int maximalRectangle_1(String[] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length();

        int[] heights = new int[cols];
        int result = 0;
        for (String s : matrix) {
            for (int j = 0; j < cols; j++) {
                if (s.charAt(j) == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            result = Math.max(result, largestRectangleArea(heights));
        }
        return result;
    }

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
        String[] matrix = {};
        System.out.println(maximalRectangle(matrix));
    }
}
