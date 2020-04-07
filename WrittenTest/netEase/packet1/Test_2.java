package netEase.packet1;

import java.util.Scanner;

public class Test_2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //组数
        int size = scanner.nextInt();

        //一共有size组
        for (int i = 0; i < size; i++) {
            //广场的大小
            int n = scanner.nextInt();
            int m = scanner.nextInt();

            int[][] dp = new int[n][m];

            //障碍物的个数
            int k = scanner.nextInt();

            //障碍物的位置
            for (int k_i = 0; k_i < k; k_i++) {
                int obs_r = scanner.nextInt() - 1;
                int obs_c = scanner.nextInt() - 1;
                dp[obs_r][obs_c] = 1;
            }

            //货物的大小
            int c = scanner.nextInt();
            int d = scanner.nextInt();

            boolean flag = false;
            outer:
            for (int row = 0; row <= n - c; row++) {
                for (int col = 0; col <= m - d; col++) {
                    if (dp[row][col] == 0 && canPut(c, d, row, col, dp)) {
                        flag = true;
                        break outer;
                    }
                }
            }

            if (flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }

    }

    static boolean canPut(int c, int d, int row, int col, int[][] dp) {
        for (int i = row; i < row + c; i++) {
            for (int j = col; j < col + d; j++) {
                if (dp[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
