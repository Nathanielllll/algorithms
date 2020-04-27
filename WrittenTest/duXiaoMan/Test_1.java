package duXiaoMan;

import java.util.Scanner;

public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int data_rows = scanner.nextInt();
        int data_cols = scanner.nextInt();
        int win_rows = scanner.nextInt();
        int win_cols = scanner.nextInt();


//        int data_rows = 4;
//        int data_cols = 5;
//        int win_rows = 5;
//        int win_cols = 5;
//

        int[][] data = new int[data_rows][data_cols];
        for (int i = 0; i < data_rows; i++) {
            for (int j = 0; j < data_cols; j++) {
                data[i][j] = ((i+1) * (j + 1)) % 10;
            }
        }

        if (data_rows < win_rows) {
            win_rows = data_rows;
        }
        if (data_cols < win_cols) {
            win_cols = data_cols;
        }


        int res = 0;
        for (int i = 0; i <= data_rows - win_rows; i++) {
            for (int j = 0; j <= data_cols - win_cols; j++) {
                res += findMax(data, win_rows, win_cols, i, j);
            }
        }

        System.out.println(res);
    }

    public static int findMax(int[][] data, int win_rows, int win_cols, int row, int col){
        int max = 0;
        for (int i = row; i < row + win_rows && i < data.length; i++) {
            for (int j = col; j < col + win_cols && j < data[0].length; j++) {
                max = Math.max(max, data[i][j]);
            }
        }
        return max;
    }
}
