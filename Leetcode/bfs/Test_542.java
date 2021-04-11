package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
题干说找"最近的0的距离"，最短路问题第一个想法就是BFS。

找01矩阵中所有元素的距离0的位置：元素0和自身的距离是0，元素1和0的距离等于0到1的距离。

用一个标记数组记录每个位置是否已经计算过距离。

初始化结果集和队列，遍历矩阵找到所有等于0的位置，结果集对应位置赋值0并且坐标入队。计算过距离的位置标记。

广搜，队列中元素出队后向四个方向分别搜索一次寻找1(没有被标记过的位置就是1)，如果搜索位置存在1则记录结果集距离为结果集中搜索源点的值+1，并且入队、标记。
 */
public class Test_542 {

    public static void main(String[] args) {
        //[[0,1,0],[0,1,0],[0,1,0],[0,1,0],[0,1,0]]
        int[][] matrix = {{0,0,0},{0,1,0},{1,1,1}};
        int[][] res = updateMatrix(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.println(res[i][j]);
            }
        }
    }


    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    res[i][j] = bfs(matrix, i, j, rows, cols, queue);
                }
            }
        }
        return res;
    }

    public static int bfs(int[][] matrix, int row, int col, int rows, int cols, Queue<int[]> queue) {
        int length = 0;
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            for (int i = 0; i < cnt; i++) {
                int[] poll = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nextRow = poll[0] + dx[k];
                    int nextCol = poll[1] + dy[k];
                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                        if (matrix[nextRow][nextCol] == 0) {
                            length++;
                            return length;
                        } else {
                            queue.add(new int[]{nextRow, nextCol});
                        }
                    }
                }
            }
            length++;
        }
        return length;
    }
}
