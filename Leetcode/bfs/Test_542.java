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


    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static class Pair{
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[][]{};
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] res = new int[rows][cols];

        HashSet<Pair> visited;
        Queue<Pair> queue;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    visited = new HashSet<>();
                    queue = new LinkedList<>();
                    //bfs
                    int min = bfs(matrix, i, j, visited, queue);
                    res[i][j] = min;
                }
            }
        }
        return res;
    }

    public static int bfs(int[][] matrix, int row, int col,
                          HashSet<Pair> visited, Queue<Pair> queue){
        visited.add(new Pair(row, col));
        queue.add(new Pair(row, col));

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = size - 1; i >= 0; i--) {
                Pair pair = queue.poll();
                int r = pair.row;
                int c = pair.col;
                for (int j = 0; j < 4; j++) {
                    int nextRow = r + dx[j];
                    int nextCol = c + dy[j];
                    if (nextRow >= 0 && nextRow < matrix.length && nextCol >= 0 && nextCol < matrix[0].length) {
                        if (matrix[nextRow][nextCol] == 0) {
                            step++;
                            return step;
                        }else if(matrix[nextRow][nextCol] == 1
                                && !visited.contains(new Pair(nextRow, nextCol))){
                            visited.add(new Pair(nextRow, nextCol));
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
