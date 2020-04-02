package array;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 *
 */
public class Test_74 {
    public static boolean searchMatrix(int[][] matrix, int number) {
        if (matrix == null) {
            return false;
        }

        //左下角开始的坐标
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col <= matrix[0].length - 1) {
            if (number > matrix[row][col]) {
                col++;
            } else if (number < matrix[row][col]) {
                row--;
            } else {
                return true;
            }
        }
        return false;
    }
}
