package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class Test_54 {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int tRow = 0;
        int tCol = 0;
        int dRow = matrix.length - 1;
        int dCol = matrix[0].length - 1;
        while (tRow <= dRow && tCol <= dCol) {
            //一行
            if (tRow == dRow) {
                while (tCol <= dCol) {
                    result.add(matrix[tRow][tCol++]);
                }
            }
            //一列
            else if (tCol == dCol) {
                while (tRow <= dRow) {
                    result.add(matrix[tRow++][tCol]);
                }
            } else {
                //正常情况
                int curRow = tRow;
                int curCol = tCol;
                while (curCol < dCol) {
                    result.add(matrix[tRow][curCol++]);
                }
                while (curRow < dRow) {
                    result.add(matrix[curRow++][dCol]);
                }
                while (curCol > tCol) {
                    result.add(matrix[dRow][curCol--]);
                }
                while (curRow > tRow) {
                    result.add(matrix[curRow--][tCol]);
                }
            }
            tRow++;
            tCol++;
            dRow--;
            dCol--;
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix_2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix_3 = {{1,2,3,4,5,6,7,8,9,10}};
        System.out.println(spiralOrder(matrix));
    }
}
