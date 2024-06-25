package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含m x n个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例2:
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
        int tRow = 0;
        int tCol = 0;
        int dRow = matrix.length - 1;
        int dCol = matrix[0].length - 1;

        while (tRow <= dRow && tCol <= dCol) {
            spiralOrderProcess(matrix, tRow++, tCol++, dRow--, dCol--, result);
        }
        return result;
    }

    private static void spiralOrderProcess(int[][] matrix, int upRow, int upCol, int downRow,
        int downCol, List<Integer> result) {
        if (upRow == downRow) {
            int times1 = downCol - upCol;
            for (int i = 0; i <= times1; i++) {
                result.add(matrix[upRow][upCol + i]);
            }
        } else if (upCol == downCol) {
            int times2 = downRow - upRow;
            for (int i = 0; i <= times2; i++) {
                result.add(matrix[upRow + i][downCol]);
            }
        } else {
            int times1 = downCol - upCol;
            int times2 = downRow - upRow;
            for (int i = 0; i < times1; i++) {
                result.add(matrix[upRow][upCol + i]);
            }
            for (int i = 0; i < times2; i++) {
                result.add(matrix[upRow + i][downCol]);
            }
            for (int i = 0; i < times1; i++) {
                result.add(matrix[downRow][downCol - i]);
            }
            for (int i = 0; i < times2; i++) {
                result.add(matrix[downRow - i][upCol]);
            }
        }

    }



    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix_2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix_3 = {{1,2,3,4,5,6,7,8,9,10}};
        System.out.println(spiralOrder(matrix));
    }
}
