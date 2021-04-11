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
    static class Pointer{
        int row;
        int col;

        public Pointer(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static int orangesRotting(int[][] grid) {
        int count = 0;

        Queue<Pointer> queue = new LinkedList<>();
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
                if (grid[i][j] == 2) {
                    queue.add(new Pointer(i, j));
                }
            }
        }

        int round = 0;
        while (count > 0 && !queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                Pointer pointer = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nextRow = pointer.row + dx[k];
                    int nextCol = pointer.col + dy[k];
                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                            && grid[nextRow][nextCol] == 1) {
                        grid[nextRow][nextCol] = 2;
                        queue.add(new Pointer(nextRow, nextCol));
                        count--;
                    }
                }
            }
            round++;
        }
        return count > 0 ? -1 : round;
    }
}
