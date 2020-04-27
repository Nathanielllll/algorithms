package dp;

/***
 * 礼物的最大价值。从棋盘的左上角开始拿格子的礼物
 * 使用动态规划：f(i,j)=max{f(i-1,j), f(i,j-1)} + gift(i,j)
 */
public class Code_47 {
    public static int maxGiftValue(int[][] matrix) {
        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            throw new RuntimeException("invalid input");
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] maxValues = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int up = 0;
                int left = 0;
                if (i > 0) {
                    up = maxValues[i - 1][j];
                }
                if (j > 0) {
                    left = maxValues[i][j - 1];
                }
                maxValues[i][j] = Math.max(up, left) + matrix[i][j];
            }
        }
        return maxValues[rows - 1][cols - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}};
        System.out.println(maxGiftValue(matrix));
    }
}
