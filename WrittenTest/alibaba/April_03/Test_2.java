package alibaba.April_03;

import java.util.Scanner;

/**
 * 第二题：
 * 一个矩阵格子，每个值代表从这走过所消耗的体力值，从最上一行任意格子走到最下一行任意格子，每次可以走上下左右四个方向，求消耗的最小体力值。
 * 输入：第一行两个数为n,m，代表矩阵格子的长宽，剩下n行，每行m个数，代表矩阵格子每格会消耗的体力值a
 * 输出：消耗的最小体力值
 * 例如：
 * 输入：
 * 3 4
 * 9 9 1 1
 * 9 1 1 9
 * 1 1 9 9
 * 输出
 * 4
 */
public class Test_2 {

    static int res = Integer.MAX_VALUE;
    static int cnt = 0;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        dp = new int[n][m];
        int[][] array = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                array[i][j] = scanner.nextInt();
            }
        }
        for (int j = 0; j < m; j++) {
            dfs(array, 0, j, 0);
        }
        System.out.println(res);
    }

    /**
     * @param array
     * @param i     ==row
     * @param j     ==col
     * @param count 消耗的最小体力值
     */
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void dfs(int[][] array, int row, int col, int count) {
        if (row < 0 || col < 0 || col >= array[0].length ||
                //如果此时同一个值已经被遍历过并且取值更小，则没必要再对它遍历
                (row < array.length && dp[row][col] > 0 && array[row][col] + count > dp[row][col])) {
            return;
        }
        if (row == array.length) {
            res = Math.min(res, count);
            return;
        }

        //用来记录遍历到该点所消耗的体力值
        int count1 = count + array[row][col];
        dp[row][col] = count1;

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];
            dfs(array, nextRow, nextCol, count1);
        }
    }
}
