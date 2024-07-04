package graph.unionFind;

import java.util.PriorityQueue;

/**
 * 给你一个points数组，表示 2D 平面上的一些点，其中points[i] = [xi, yi]。
 * <p>
 * 连接点[xi, yi] 和点[xj, yj]的费用为它们之间的 曼哈顿距离：|xi - xj| + |yi - yj|，其中|val|表示val的绝对值。
 * <p>
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有一条简单路径时，才认为所有点都已连接。
 * <p>
 * <p>
 * 最小生成树Kruskal算法
 *
 * 1.将所有边从小到大进行排序
 * 2.依次从最小的边开始添加
 *   2.1、添加当前边不能导致环
 *   2.2、添加了k-1条边时退出循环（k个结点的最小生成树有k-1条边）
 */
public class Test_1584 {
    public int minCostConnectPoints(int[][] points) {
    int n = points.length;
    // int[0] 是两点距离，int[1]是idx1, int[1]是idx2
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int[] edge = calEdge(points, i, j);
        minHeap.add(edge);
      }
    }

    Djset djset = new Djset(n);
    int result = 0;
    int edgeCnt = 0;
    while (!minHeap.isEmpty()) {
      int[] edge = minHeap.poll();
      if (djset.union(edge[1], edge[2])) {
        result += edge[0];
        edgeCnt++;
      }
      if (edgeCnt == n - 1) {
        break;
      }
    }
    return result;
  }

  private int[] calEdge(int[][] points, int idx1, int idx2) {
    int dist =
        Math.abs(points[idx1][0] - points[idx2][0]) + Math.abs(points[idx1][1] - points[idx2][1]);
    return new int[]{dist, idx1, idx2};
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
