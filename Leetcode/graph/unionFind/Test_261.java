package graph.unionFind;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of
 * nodes), write a function to check whether these edges make up a valid tree.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
 * <p>
 * Output: true
 */
public class Test_261 {

  public boolean validTree(int n, int[][] edges) {
    Djset djset = new Djset(n);
    int result = n;
    for (int[] edge : edges) {
      int unionRes = djset.union(edge[0], edge[1]);
      if (unionRes == 0) {
        return false;
      } else if (unionRes == 1) {
        result--;
      }
    }
    return result == 1;
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
