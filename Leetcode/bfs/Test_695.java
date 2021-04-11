package bfs;

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


    //=====================================bfs==============================
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    public static int maxAreaOfIsland_1(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int result = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    result = Math.max(result, bfs(matrix, rows, cols, queue));
                }
            }
        }
        return result;
    }

    public static int bfs(int[][] matrix, int rows, int cols, Queue<int[]> queue) {
        int area = 1;
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                int[] poll = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nextRow = poll[0] + dx[k];
                    int nextCol = poll[1] + dy[k];
                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                            && matrix[nextRow][nextCol] == 1) {
                        matrix[nextRow][nextCol] = 0;
                        queue.add(new int[]{nextRow, nextCol});
                        area++;
                    }
                }
            }
        }
        return area;
    }
}
