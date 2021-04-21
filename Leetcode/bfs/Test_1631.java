package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author luzhi
 * @date 2021/4/21
 */
public class Test_1631 {

    /*
    你准备参加一场远足活动。给你一个二维rows x columns的地图heights，其中heights[row][col]表示格子(row, col)的高度。
    一开始你在最左上角的格子(0, 0)，且你希望去最右下角的格子(rows-1, columns-1)（注意下标从 0 开始编号）。
    你每次可以往 上，下，左，右四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

    一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。

    请你返回从左上角走到右下角的最小体力消耗值。
     */
    // 二分法+bfs
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int minimumEffortPath(int[][] heights) {
        int left = 0;
        int right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (bfs(heights, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean bfs(int[][] heights, int diff) {
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] visited = new boolean[rows][cols];
        visited[0][0] = true;
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{0, 0});

        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                Integer[] poll = queue.poll();
                if (poll[0] == rows - 1 && poll[1] == cols - 1) {
                    return true;
                }
                for (int k = 0; k < 4; k++) {
                    int nextRow = poll[0] + dx[k];
                    int nextCol = poll[1] + dy[k];
                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && !visited[nextRow][nextCol]
                            && Math.abs(heights[nextRow][nextCol] - heights[poll[0]][poll[1]]) <= diff) {
                        queue.add(new Integer[]{nextRow, nextCol});
                        visited[nextRow][nextCol] = true;
                    }
                }
            }
        }
        return false;
    }
}
