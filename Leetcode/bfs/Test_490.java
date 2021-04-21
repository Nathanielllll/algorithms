package bfs;

import dp.matrixPath.FindMinPath;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author luzhi
 * @date 2021/4/20
 */
public class Test_490 {
    /*
    在迷宫中有一个球，里面有空的空间和墙壁。球可以通过滚上，下，左或右移动，
但它不会停止滚动直到撞到墙上。当球停止时，它可以选择下一个方向。

给定球的起始位置，目的地和迷宫，确定球是否可以停在终点。

迷宫由二维数组表示。1表示墙和0表示空的空间。你可以假设迷宫的边界都是墙。开始和目标坐标用行和列索引表示。

样例
例1:

输入:
map =
[
 [0,0,1,0,0],
 [0,0,0,0,0],
 [0,0,0,1,0],
 [1,1,0,1,1],
 [0,0,0,0,0]
]
start = [0,4]
end = [3,2]
输出:
false
     */
    public static void main(String[] args) {
        Test_490 test = new Test_490();
        int[][] matrix = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int[] start = new int[]{0,4};
        int[] end = new int[]{4,4};
        System.out.println(test.hasPath(matrix, start, end));
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

    boolean hasPath(int[][] matrix, int[] start, int[] end) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        visited[start[0]][start[1]] = true;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(start[0], start[1]));

        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                Pair poll = queue.poll();
                if (poll.row == end[0] && poll.col == end[1]) {
                    return true;
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
                    }
                }
            }
        }
        return false;
    }
}
