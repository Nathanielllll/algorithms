package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * <p>
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * <p>
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * <p>
 * 返回网格坐标 result 的 2D 列表 ，其中 result[i] = [ri, ci] 表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。 输入: heights
 * = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]] 输出:
 * [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]] 示例 2：
 * <p>
 * 输入: heights = [[2,1],[1,2]] 输出: [[0,0],[0,1],[1,0],[1,1]]
 */
public class Test_417 {

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    int m = heights.length;
    int n = heights[0].length;
    Set<Integer> pacific = new HashSet<>();
    Set<Integer> atlantic = new HashSet<>();
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // pacific
        if (i == 0 || j == 0) {
          pacificAtlanticDfs(heights, pacific, directs, m, n, i, j);
        }
        // atlantic
        if (i == m - 1 || j == n - 1) {
          pacificAtlanticDfs(heights, atlantic, directs, m, n, i, j);
        }
      }
    }
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int coord = i * n + j;
        if (pacific.contains(coord) && atlantic.contains(coord)) {
          res.add(Arrays.asList(i, j));
        }
      }
    }
    return res;
  }

  private void pacificAtlanticDfs(int[][] heights, Set<Integer> visited, int[][] directs,
      int m, int n, int i, int j) {
    int coor = i * n + j;
    if (visited.contains(coor)) {
      return;
    }
    visited.add(coor);

    for (int k = 0; k < 4; k++) {
      int x = i + directs[k][0];
      int y = j + directs[k][1];
      if (x >= 0 && x < m && y >= 0 && y < n && heights[x][y] >= heights[i][j]) {
        pacificAtlanticDfs(heights, visited, directs, m, n, x, y);
      }
    }
  }
}
