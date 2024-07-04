package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a 𝑚 × 𝑛 m×n 2D grid initialized with these three possible values:
 * <p>
 * -1 - A water cell that can not be traversed. 0 - A treasure chest. INF - A land cell that can be
 * traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF. Fill each land cell with
 * the distance to its nearest treasure chest. If a land cell cannot reach a treasure chest than the
 * value should remain INF.
 * <p>
 * Assume the grid can only be traversed up, down, left, or right.
 * <p>
 * Example 1:
 * <p>
 * Input: [ [2147483647,-1,0,2147483647], [2147483647,2147483647,2147483647,-1],
 * [2147483647,-1,2147483647,-1], [0,-1,2147483647,2147483647] ]
 */
public class Test_286 {

  public void islandsAndTreasure(int[][] grid) {
    islandsAndTreasureBfs(grid);
  }

  // bfs会把最近的inf, 率先赋值掉了。而dfs不同，要看岛屿开始的位置，所以不同的岛屿出发，inf的被赋的值也不同！
  private void islandsAndTreasureBfs(int[][] grid) {
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    // bfs，找到不同岛屿离宝藏的level距离
    Queue<int[]> queue = new LinkedList<>();
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0) {
          queue.add(new int[]{i, j});
        }
      }
    }

    while (!queue.isEmpty()) {
      int[] treasure = queue.poll();
      int row = treasure[0];
      int col = treasure[1];
      for (int i = 0; i < 4; i++) {
        int x = row + directs[i][0];
        int y = col + directs[i][1];
        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == Integer.MAX_VALUE) {
          grid[x][y] = grid[row][col] + 1;
          queue.add(new int[]{x, y});
        }
      }
    }
  }
}
