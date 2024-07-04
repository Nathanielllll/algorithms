package graph.unionFind;

/**
 * There is an undirected graph with n nodes. There is also an edges array, where edges[i] = [a, b]
 * means that there is an edge between node a and node b in the graph.
 * <p>
 * Return the total number of connected components in that graph.
 * <p>
 * Example 1:
 * <p>
 * Input: n=3 edges=[[0,1], [0,2]]
 * <p>
 * Output: 1
 */
public class Test_323 {

  public int countComponents(int n, int[][] edges) {
    Djset djset = new Djset(n);
    int result = n;
    for (int[] edge : edges) {
      if (djset.union(edge[0], edge[1]) == 1) {
        result--;
      }
    }
    return result;
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
