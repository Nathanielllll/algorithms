package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author luzhi
 * @date 2021/3/1
 */
public class Test_994 {
    /*
    在给定的网格中，每个单元格可以有以下三个值之一：

    值0代表空单元格；
    值1代表新鲜橘子；
    值2代表腐烂的橘子。
    每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。

    返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回-1。

    输入：[[2,1,1],[1,1,0],[0,1,1]]
    输出：4
     */
    public int orangesRotting(int[][] grid) {
        return orangesRottingBfs(grid);
    }

    private static int orangesRottingBfs(int[][] grid) {
        int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
                if (grid[i][j] == 1) {
                    ++fresh;
                }
            }
        }

        boolean flag = false;
        int level = 0;
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            // 第一次不计入
            if (flag) {
                level++;
            } else {
                flag = true;
            }
            for (int i = 0; i < cnt; i++) {
                int[] treasure = queue.poll();
                int row = treasure[0];
                int col = treasure[1];
                for (int j = 0; j < 4; j++) {
                    int x = row + directs[j][0];
                    int y = col + directs[j][1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        --fresh;
                        grid[x][y] = 2;
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
        return fresh == 0 ? level : -1;
    }
}
