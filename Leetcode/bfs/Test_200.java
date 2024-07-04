package bfs;

public class Test_200 {

  public int numIslands(char[][] grid) {
    int landsCnt = 0;
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          ++landsCnt;
          numIslandsDfs(grid, i, j, m, n);
        }
      }
    }
    return landsCnt;
  }

  private void numIslandsDfs(char[][] grid, int i, int j, int m, int n) {
    if (i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == '1') {
      grid[i][j] = '0';
      numIslandsDfs(grid, i - 1, j, m, n);
      numIslandsDfs(grid, i + 1, j, m, n);
      numIslandsDfs(grid, i, j - 1, m, n);
      numIslandsDfs(grid, i, j + 1, m, n);
    }
  }
}
