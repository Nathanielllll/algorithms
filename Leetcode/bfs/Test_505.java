package bfs;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author luzhi
 * @date 2021/4/20
 */
public class Test_505 {
    public static void main(String[] args) {
        Test_505 test = new Test_505();
        int[][] matrix = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = new int[]{0, 4};
        int[] end = new int[]{3, 2};
        System.out.println(test.shortestDistance(matrix, start, end));
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

    int shortestDistance(int[][] matrix, int[] start, int[] end) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        visited[start[0]][start[1]] = true;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(start[0], start[1]));

        int[][] distances = new int[rows][cols];

        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                Pair poll = queue.poll();
                if (poll.row == end[0] && poll.col == end[1]) {
                    break;
                }
                for (int k = 0; k < 4; k++) {
                    int nextRow = poll.row;
                    int nextCol = poll.col;
                    while (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                            && matrix[nextRow][nextCol] == 0) {
                        nextRow += dx[k];
                        nextCol += dy[k];
                    }
                    nextRow -= dx[k];
                    nextCol -= dy[k];
                    if (!visited[nextRow][nextCol]) {
                        visited[nextRow][nextCol] = true;
                        queue.add(new Pair(nextRow, nextCol));
                        distances[nextRow][nextCol] = distances[poll.row][poll.col]
                                + Math.abs(nextRow - poll.row) + Math.abs(nextCol - poll.col);
                    }
                }
            }
        }
        return distances[end[0]][end[1]] != 0 ? distances[end[0]][end[1]] : -1;
    }
}
