package dp;

import java.util.Arrays;

/**
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
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
}
