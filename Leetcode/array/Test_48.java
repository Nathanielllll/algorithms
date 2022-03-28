package array;

import java.util.Arrays;

/**
 * 给定一个 n×n 的二维矩阵表示一个图像。
 *
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 *
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 *
 */
public class Test_48 {
    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) {
            return;
        }

        int tRow = 0;
        int tCol = 0;
        int dRow = matrix.length - 1;
        int dCol = matrix[0].length - 1;

        while (tRow <= dRow) {
            rotateOnce(matrix, tRow++, tCol++, dRow--, dCol--);
        }
    }

    private static void rotateOnce(int[][] matrix, int tRow, int tCol, int dRow, int dCol){
        int times = dRow - tRow;
        for (int i = 0; i < times; i++) {
            int temp = matrix[tRow][tCol + i];
            matrix[tRow][tCol + i] = matrix[dRow - i][tCol];
            matrix[dRow - i][tCol] = matrix[dRow][dCol - i];
            matrix[dRow][dCol - i] = matrix[tRow + i][dCol];
            matrix[tRow + i][dCol] = temp;
        }

    }

    /**
     * [
     * [5,1,9,11],
     * [2,4,8,10],
     * [13,3,6,7],
     * [15,14,12,16]
     * ]
     *
     *
     * [
     * [15,13,2,5],
     * [14,3,4,1],
     * [12,6,8,9],
     * [16,7,10,11]
     * ]
     * @param args
     */
    public static void main(String[] args) {
        int[][] matrix = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
//        int[][] matrix = {{4,8},{3,6}};
        rotate(matrix);
        System.out.println(Arrays.toString(matrix[0]));
        System.out.println(Arrays.toString(matrix[1]));
        System.out.println(Arrays.toString(matrix[2]));
        System.out.println(Arrays.toString(matrix[3]));
    }
}
