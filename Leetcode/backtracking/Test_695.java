package backtracking;

import bfs.Test_130;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 */
public class Test_695 {
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] used = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, subProcess(grid, i, j, rows, cols, used));
                }
            }
        }
        return res;
    }

    public int subProcess(int[][] grid, int row, int col, int rows, int cols,
                          boolean[][] used){
        int count = 0;
        if(row >= 0 && row < rows && col >= 0 && col < cols
                && !used[row][col] && grid[row][col] == 1){
            used[row][col] = true;
            count = 1 +
                    subProcess(grid, row + 1, col, rows, cols, used) +
                    subProcess(grid, row - 1, col, rows, cols, used) +
                    subProcess(grid, row, col + 1, rows, cols, used) +
                    subProcess(grid, row, col - 1, rows, cols, used);
        }
        return count;
    }

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    private class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int maxAreaOfIsland_1(int[][] grid) {
        int res = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] used = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, bfs(grid, i, j, used));
                }
            }
        }
        return res;
    }

    private int bfs(int[][] grid, int i, int j, boolean[][] used){
        int count = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
        while (!queue.isEmpty()) {
            Point temp = queue.poll();
            int row = temp.row;
            int col = temp.col;
            if(row >= 0 && row < grid.length && col >= 0 && col < grid[0].length
                && !used[row][col] && grid[row][col] == 1){
                used[row][col] = true;
                count++;
                for (int k = 0; k < 4; k++) {
                    queue.add(new Point(row + dx[k], col + dy[k]));
                }
            }
        }
        return count;
    }
}
