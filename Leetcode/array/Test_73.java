package array;

import java.util.Arrays;

/**
 * 给定一个m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例1:
 *
 * 输入:
 * [
 *  [1,1,1],
 *  [1,0,1],
 *  [1,1,1]
 * ]
 * 输出:
 * [
 *  [1,0,1],
 *  [0,0,0],
 *  [1,0,1]
 * ]
 *
 */
public class Test_73 {
    public static void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] row = new int[rows];
        int[] col = new int[cols];

        // 记录为0的位置
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,0,1}};
        setZeroes(matrix);
        System.out.println(Arrays.toString(matrix[0]));
//        System.out.println(Arrays.toString(matrix[1]));
//        System.out.println(Arrays.toString(matrix[2]));
    }
}
