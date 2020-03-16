package backtracking;

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
}
