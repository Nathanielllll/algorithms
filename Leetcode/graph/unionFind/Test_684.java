package graph.unionFind;

/**
 * 树可以看成是一个连通且 无环 的 无向 图。
 * <p>
 * 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 n 的二维数组
 * edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
 * <p>
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的那个。
 */
public class Test_684 {

  public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    Djset djset = new Djset(n);
    for (int[] edge : edges) {
      int unionRes = djset.union(edge[0] - 1, edge[1] - 1);
      if (unionRes == 0) {
        return edge;
      }
    }
    return new int[]{};
  }

  static class Djset {

    int[] parent;
    int[] rank;

    public Djset(int length) {
      parent = new int[length];
      rank = new int[length];
      for (int i = 0; i < length; i++) {
        parent[i] = i;
      }
    }

    public int findRoot(int val) {
      if (val != parent[val]) {
        parent[val] = findRoot(parent[val]);
      }
      return parent[val];
    }

    public int union(int x, int y) {
      int xRoot = findRoot(x);
      int yRoot = findRoot(y);
      if (xRoot == yRoot) {
        return 0;
      } else {
        if (rank[xRoot] < rank[yRoot]) {
          parent[xRoot] = yRoot;
        } else if (rank[yRoot] < rank[xRoot]) {
          parent[yRoot] = xRoot;
        } else {
          parent[xRoot] = yRoot;
          rank[yRoot]++;
        }
      }
      return 1;
    }
  }
}
