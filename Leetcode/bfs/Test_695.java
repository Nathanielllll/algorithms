package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个包含了一些 0 和 1的非空二维数组grid, 一个岛屿是由四个方向 (水平或垂直) 的1(代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
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
 * 对于上面这个给定矩阵应返回6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回0。
 */
public class Test_695 {
    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int areaOfIsland = maxAreaOfIslandDfs(grid, i, j, m, n);
                    result = Math.max(result, areaOfIsland);
                }
            }
        }
        return result;
    }

    private int maxAreaOfIslandDfs(int[][] grid, int i, int j, int m, int n) {
        int cnt = 0;
        if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1) {
            ++cnt;
            grid[i][j] = 0;
            cnt =
                cnt + maxAreaOfIslandDfs(grid, i - 1, j, m, n) + maxAreaOfIslandDfs(grid, i + 1, j, m, n)
                    + maxAreaOfIslandDfs(grid, i, j - 1, m, n) + maxAreaOfIslandDfs(grid, i, j + 1, m, n);
        }
        return cnt;
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
