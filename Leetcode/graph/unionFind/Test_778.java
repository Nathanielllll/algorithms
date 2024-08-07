package graph.unionFind;

import java.util.PriorityQueue;

/**
 * @author luzhi
 * @date 2021/4/8
 */
public class Test_778 {

  /*
  在一个 N x N 的坐标方格grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。

  现在开始下雨了。当时间为t时，此时雨水导致水池中任意位置的水位为t。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。

  你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台(N-1, N-1)？


  示例 1:

  输入: [[0,2],[1,3]]
  输出: 3
  解释:
  时间为0时，你位于坐标方格的位置为 (0, 0)。
  此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。

  等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
  示例2:

  输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
  输出: 16
  解释:
   0  1  2  3  4
  24 23 22 21  5
  12 13 14 15 16
  11 17 18 19 20
  10  9  8  7  6

  最终的路线用加粗进行了标记。
  我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
   */
  public int swimInWater(int[][] grid) {
    int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    int m = grid.length;
    int n = grid[0].length;
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        minHeap.offer(new int[]{grid[i][j], i, j});
      }
    }

    int firstIdx = calIdx(0, 0, n);
    int lastIdx = calIdx(m - 1, n - 1, n);

    Djset djset = new Djset(m * n);
    while (!minHeap.isEmpty()) {
      int[] info = minHeap.poll();
      int val = info[0];
      int row = info[1];
      int col = info[2];
      for (int i = 0; i < 4; i++) {
        int nextRow = row + directs[i][0];
        int nextCol = col + directs[i][1];
        if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n
            && val >= grid[nextRow][nextCol]) {
          int curIdx = calIdx(row, col, n);
          int nextIdx = calIdx(nextRow, nextCol, n);
          djset.union(curIdx, nextIdx);
        }
        if (djset.findRoot(firstIdx) == djset.findRoot(lastIdx)) {
          return val;
        }
      }
    }
    return -1;
  }

  private int calIdx(int i, int j, int n) {
    return i * n + j;
  }

  class Djset {

    int[] parent;
    int[] rank;

    public Djset(int length) {
      parent = new int[length];
      rank = new int[length];
      for (int i = 0; i < length; i++) {
        parent[i] = i;
      }
    }

    public int findRoot(int value) {
      if (value != parent[value]) {
        parent[value] = findRoot(parent[value]);
      }
      return parent[value];
    }

    public boolean union(int x, int y) {
      int xRoot = findRoot(x);
      int yRoot = findRoot(y);
      if (xRoot == yRoot) {
        return false;
      }

      if (rank[xRoot] < rank[yRoot]) {
        parent[xRoot] = yRoot;
      } else if (rank[yRoot] < rank[xRoot]) {
        parent[yRoot] = xRoot;
      } else {
        rank[yRoot]++;
        parent[xRoot] = yRoot;
      }
      return true;
    }
  }

}
