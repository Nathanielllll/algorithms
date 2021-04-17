package dp;

/*
给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1,col1) ，右下角为 (row2,col2)。
上图子矩阵左上角(row1, col1) = (2, 1)，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。

示例:

给定 matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12

 */
public class Test_304 {
    static class NumMatrix {
        int[][] sums;

        public NumMatrix(int[][] matrix) {
            if(matrix==null||matrix.length==0){
                return;
            }
            int rows = matrix.length;
            int cols = matrix[0].length;
            sums = matrix.clone();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int up = 0;
                    if (i != 0) {
                        up = matrix[i - 1][j];
                    }
                    sums[i][j] += up;
                }
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int left = 0;
                    if (j != 0) {
                        left = matrix[i][j - 1];
                    }
                    sums[i][j] += left;
                }
            }
        }


        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (row1 == 0 && col1 == 0) {
                return sums[row2][col2];
            }else if (row1 == 0) {
                return sums[row2][col2] - sums[row2][col1 - 1];
            } else if (col1 == 0) {
                return sums[row2][col2] - sums[row1 - 1][col2];
            }else {
                return sums[row2][col2] - sums[row2][col1 - 1] - sums[row1 - 1][col2] + sums[row1 - 1][col1 - 1];
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix test = new NumMatrix(matrix);
//        sumRegion(2, 1, 4, 3) -> 8
//        sumRegion(1, 1, 2, 2) -> 11
//        sumRegion(1, 2, 2, 4) -> 12
        System.out.println(test.sumRegion(2, 1, 4, 3));
        System.out.println(test.sumRegion(1, 1, 2, 2));
        System.out.println(test.sumRegion(1, 2, 2, 4));


    }
}
