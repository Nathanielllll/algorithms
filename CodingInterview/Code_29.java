/**
 * 顺时针打印矩阵
 */
public class Code_29 {

    public static void printMatrixClockWisely(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int tRow = 0;
        int tCol = 0;
        int dRow = matrix.length - 1;
        int dCol = matrix[0].length - 1;
        while (tRow <= dRow && tCol <= dCol) {
            printEdge(matrix, tRow++, tCol++, dRow--, dCol--);
        }
    }

    //旋转一圈
    public static void printEdge(int[][] matrix, int tRow, int tCol, int dRow, int dCol) {
        //一行的情况
        if (tRow == dRow) {
            while (tCol <= dCol) {
                System.out.print(matrix[tRow][tCol++] + " ");
            }
        }
        //一列的情况
        else if (tCol == dCol) {
            while (tRow <= dRow) {
                System.out.print(matrix[tRow++][tCol] + " ");
            }
        }
        //其他情况
        else {
            int curRow = tRow;
            int curCol = tCol;
            //从第一行向右打印
            while (curCol < dCol) {
                System.out.print(matrix[tRow][curCol++] + " ");
            }
            //从最后一列列向下打印
            while (curRow < dRow) {
                System.out.print(matrix[curRow++][dCol] + " ");
            }
            //从最后一行向左打印
            while (curCol > tCol) {
                System.out.print(matrix[dRow][curCol--] + " ");
            }
            //从第一列向上打印
            while (curRow > tRow) {
                System.out.print(matrix[curRow--][tCol] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                };
//        int[][] matrix = {{1, 2, 3}, {5, 6, 7}, {9, 10, 11},
//                {13, 14, 15}};
//        int[][] matrix_3 = {{1,2,3,4,5,6,7,8,9,10}};
//        int[][] ma
        printMatrixClockWisely(matrix);
    }
}
