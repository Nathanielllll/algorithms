package netEase.April_07;

import java.util.Scanner;

//3 3
//        101
//        010
//        101

/**
 * AC 100%
 */
public class Test_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        int[][] arr = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            String values = scanner.next();
            for (int j = 0; j < cols; j++) {
                String num = values.substring(j, j+1);
                arr[i][j] = Integer.parseInt(num);
            }
        }


//        //Test:
//        int rows = 3;
//        int cols = 3;
//        int[][] arr = {{1,0,1},{0,1,0},{1,0,1}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (arr[i][j] == 1) {
                    backTacking(arr, rows, cols, i, j);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j < cols - 1) {
                    System.out.print(arr[i][j] + " ");
                }else {
                    System.out.println(arr[i][j]);
                }

            }
        }
    }

    //分别表示左移、下移、右移、上移
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void backTacking(int[][] arr, int rows, int cols, int row, int col){
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int x = row + dx[i];
            int y = col + dy[i];
            if (x >= 0 && x < rows &&
                    y >= 0 && y < cols) {
                min = Math.min(min, arr[x][y] + 1);
            }
        }

        if(min != arr[row][col]){
            arr[row][col] = min;
            for (int i = 0; i < 4; i++) {
                int x = row + dx[i];
                int y = col + dy[i];
                if (x >= 0 && x < rows &&
                        y >= 0 && y < cols) {
                    backTacking(arr, rows, cols, x, y);
                }
            }
        }
    }
}
