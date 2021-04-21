package dp.matrixPath;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author luzhi
 * @date 2021/4/20
 */

/**
 * 给定一个包含非负整数的 mxn网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：不限制走路的方向
 *
 * 示例:
 *
 * 输入:
 // 1,9,9,9,9
 // 1,9,1,1,1
 // 1,1,1,9,1
 // 9,9,9,9,1
 * 输出: 10
 */
public class FindMinPath {
    public static void main(String[] args) {
        FindMinPath test = new FindMinPath();
        // 1,9,9,9,9
        // 1,9,1,1,1
        // 1,1,1,9,1
        // 9,9,9,9,1

        // 为10
        int[][] grid = {{1,9,9,9,9}, {1,9,1,1,1}, {1,1,1,9,1},{9,9,9,9,1}};
        System.out.println(test.dijkstra(grid));
    }

    class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return row == pair.row && col == pair.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int dijkstra(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        HashMap<Pair, Integer> distanceMap = new HashMap<>();
        distanceMap.put(new Pair(0, 0), grid[0][0]);
        Pair minNode = getMinDistanceAndUnselectedNode(distanceMap, visited);

        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (int k = 0; k < 4; k++) {
                int nextRow = minNode.row + dx[k];
                int nextCol = minNode.col + dy[k];
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                    Pair toNode = new Pair(nextRow, nextCol);
                    if (!distanceMap.containsKey(toNode)) {
                        distanceMap.put(toNode, distance + grid[nextRow][nextCol]);
                    } else {
                        distanceMap.put(toNode,
                                Math.min(distanceMap.get(toNode), distance + grid[nextRow][nextCol]));
                    }
                }
            }
            visited[minNode.row][minNode.col] = true;
            minNode = getMinDistanceAndUnselectedNode(distanceMap, visited);
        }
        return distanceMap.get(new Pair(rows - 1, cols - 1));
    }

    private Pair getMinDistanceAndUnselectedNode(HashMap<Pair, Integer> distanceMap, boolean[][] visited) {
        Pair minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Pair, Integer> entry : distanceMap.entrySet()) {
            Pair node = entry.getKey();
            int distance = entry.getValue();
            if (!visited[node.row][node.col] && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
