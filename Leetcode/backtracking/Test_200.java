package backtracking;

import java.util.LinkedList;
import java.util.Queue;

public class Test_200 {

    // bfs

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    private class Point{
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }


        int rows = grid.length;
        int cols = grid[0].length;

        int[][] grid_int = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid_int[i][j] = grid[i][j]-'0';
            }
        }

        boolean[][] used = new boolean[rows][cols];
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid_int[i][j] == 1 && !used[i][j] && bfs(grid_int, i, j, used)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean bfs(int[][] grid, int i, int j, boolean[][] used){
        int count = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
        while(!queue.isEmpty()){
            Point temp = queue.poll();
            int row = temp.row;
            int col = temp.col;
            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length
                    && !used[row][col] && grid[row][col] == 1) {
                used[row][col] = true;
                count++;
                for (int k = 0; k < 4; k++) {
                    queue.add(new Point(row+dx[k], col+dy[k]));
                }
            }
        }
        return count > 0;
    }
}
