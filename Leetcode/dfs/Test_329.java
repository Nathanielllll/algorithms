package dfs;

/*
给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。

示例 1:

输入: nums =
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
输出: 4
解释: 最长递增路径为[1, 2, 6, 9]。
示例 2:

输入: nums =
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
输出: 4
解释: 最长递增路径是[3, 4, 5, 6]。注意不允许在对角线方向上移动。

 */
public class Test_329 {

  public int longestIncreasingPath(int[][] matrix) {
    return longestIncreasingPathDfs(matrix);
  }

  private int longestIncreasingPathDfs(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int result = 0;
    int[][] memo = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        result = Math.max(result, longestIncreasingPathDfs(matrix, i, j, m, n, directs, memo));
      }
    }
    return result;
  }

  private int longestIncreasingPathDfs(int[][] matrix, int i, int j, int m, int n,
      int[][] directs, int[][] memo) {
    if (memo[i][j] != 0) {
      return memo[i][j];
    }

    int level = 1;
    for (int k = 0; k < 4; k++) {
      int x = i + directs[k][0];
      int y = j + directs[k][1];
      if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
        level = Math.max(level, 1 + longestIncreasingPathDfs(matrix, x, y, m, n, directs, memo));
      }
    }
//    memo[i][j] = Math.max(memo[i][j], level);
    // 下面这种写法也可以的。因为memo表示从当前位置【出发】，最长的长度
    memo[i][j] = level;
    return level;
  }
}
