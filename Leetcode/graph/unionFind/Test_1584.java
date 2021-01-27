package graph.unionFind;

import java.util.*;

/**
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * <p>
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * <p>
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
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
    class Djset {
        private int[] parent;
        private int[] rank;

        public Djset(int length) {
            parent = new int[length];
            for (int i = 0; i < length; i++) {
                parent[i] = i;
            }

            rank = new int[length];
        }

        public int findRoot(int x) {
            if (x != parent[x]) {
                parent[x] = findRoot(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int x_root = findRoot(x);
            int y_root = findRoot(y);
            if (x_root == y_root) {
                return false;
            } else {
                if (rank[x_root] < rank[y_root]) {
                    parent[x_root] = y_root;
                } else if (rank[y_root] < rank[x_root]) {
                    parent[y_root] = x_root;
                } else {
                    parent[x_root] = y_root;
                    rank[y_root]++;
                }
                return true;
            }
        }
    }

    class Edge {
        int dest;
        int index1;
        int index2;

        public Edge(int dest, int index1, int index2) {
            this.dest = dest;
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    private Edge generateEdge(int[][] points, int i, int j) {
        return new Edge(Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]), i, j);
    }


    public int minCostConnectPoints(int[][] points) {
        int length = points.length;
        Djset djset = new Djset(length);

        List<Edge> list = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                Edge edge = generateEdge(points, i, j);
                list.add(edge);
            }
        }

        Collections.sort(list, ((o1, o2) -> o1.dest - o2.dest));

        int result = 0;
        int cnt = 0;
        for (Edge edge : list) {
            if (djset.union(edge.index1, edge.index2)) {
                result += edge.dest;
                cnt++;
                if (cnt == length - 1) {
                    break;
                }
            }
        }
        return result;
    }

}
