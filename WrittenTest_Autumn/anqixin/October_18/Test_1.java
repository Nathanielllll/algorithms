package anqixin.October_18;

public class Test_1 {
    public int maxValue (int[][] matrix) {
        // write code here
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] += matrix[i-1][0];
        }
        for (int j = 1; j < matrix[0].length; j++) {
            matrix[0][j] += matrix[0][j-1];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1]) + matrix[i][j];
            }
        }
        return matrix[matrix.length - 1][matrix[0].length-1];
    }
}
