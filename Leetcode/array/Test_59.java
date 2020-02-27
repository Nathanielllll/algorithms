package array;

import java.util.Arrays;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class Test_59 {
    public static int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        int tRow = 0;
        int tCol = 0;
        int dRow = n - 1;
        int dCol = n - 1;
        int num = 1;
        while (num <= n * n && tRow <= dRow && tCol <= dCol) {
            //一行
            if (tRow == dRow) {
                while (tCol <= dCol) {
                    matrix[tRow][tCol++] = num++;
                }
            }
            //一列
            else if (tCol == dCol) {
                while (tRow <= dRow) {
                    matrix[tRow++][tCol] = num++;
                }
            } else {
                //正常情况
                int curRow = tRow;
                int curCol = tCol;
                while (curCol < dCol) {
                    matrix[tRow][curCol++] = num++;
                }
                while (curRow < dRow) {
                    matrix[curRow++][dCol] = num++;
                }
                while (curCol > tCol) {
                    matrix[dRow][curCol--] = num++;
                }
                while (curRow > tRow) {
                    matrix[curRow--][tCol] = num++;
                }
            }
            tRow++;
            tCol++;
            dRow--;
            dCol--;
        }
        return matrix;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateMatrix(2)[0]));
        System.out.println(Arrays.toString(generateMatrix(2)[1]));
//        System.out.println(Arrays.toString(generateMatrix(4)[0]));
//        System.out.println(Arrays.toString(generateMatrix(4)[1]));
//        System.out.println(Arrays.toString(generateMatrix(4)[2]));
//        System.out.println(Arrays.toString(generateMatrix(4)[3]));
    }
}
