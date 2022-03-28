package bfs;

import java.util.LinkedList;
import java.util.Queue;

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
public class Test_329_bfs {
    public static void main(String[] args) {
//        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        int[][] matrix = {{3,4,5},{3,2,6},{2,2,1}};
        System.out.println(longestIncreasingPath(matrix));
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited;
        Queue<Pair> queue;

        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                visited = new boolean[rows][cols];
                queue = new LinkedList<>();
                res = Math.max(res, bfs(matrix, i, j, visited, queue));
            }
        }
        return res;
    }


    public static int bfs(int[][] matrix, int row, int col,
                          boolean[][] visited, Queue<Pair> queue) {
//        visited[row][col] = true;
        queue.add(new Pair(row, col));

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = size - 1; i >= 0; i--) {
                Pair cur = queue.poll();
                int r = cur.row;
                int c = cur.col;
                for (int j = 0; j < 4; j++) {
                    int nextRow = r + dx[j];
                    int nextCol = c + dy[j];
                    if (nextRow >= 0 && nextRow < matrix.length && nextCol >= 0 && nextCol < matrix[0].length) {
                        if(matrix[nextRow][nextCol] > matrix[r][c]){
//                            visited[nextRow][nextCol]=true;
                            queue.add(new Pair(nextRow, nextCol));
                        }
                    }
                }
            }
            step++;
        }

        return step;
    }
}
