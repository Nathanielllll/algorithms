package backtracking;

import java.util.LinkedList;
import java.util.Queue;

public class Test_200 {
    //dfs
    public int numIslands_1(char[][] grid) {
        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    dfs(i, j, visited, grid);
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, boolean[][] visited, char[][] grid) {
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int newX = i + directions[k][0];
            int newY = j + directions[k][1];
            // 如果不越界、没有被访问过、并且还要是陆地
            if (inArea(newX, newY) && grid[newX][newY] == '1' && !visited[newX][newY]) {
                dfs(newX, newY, visited, grid);
            }
        }
    }

    private int rows;
    private int cols;
    //左、下、右、上
    private int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    //使用了bfs
    public int numIslands(char[][] grid) {
        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果是岛屿中的一个点，并且没有被访问过
                // 从坐标为 (i,j) 的点开始进行广度优先遍历
                if (!visited[i][j] && grid[i][j] == '1') {
                    count++;
                    Queue<Integer> queue = new LinkedList<>();
                    // 小技巧：把坐标转换为一个数字
                    queue.add(i * cols + j);
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        int cur = queue.poll();
                        int curX = cur / cols;
                        int curY = cur % cols;
                        for (int k = 0; k < 4; k++) {
                            int newX = curX + directions[k][0];
                            int newY = curY + directions[k][1];
                            if(inArea(newX, newY) && grid[newX][newY] == '1' && !visited[newX][newY]){
                                queue.add(newX * cols + newY);
                                visited[newX][newY] = true;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    private boolean inArea(int x, int y) {
        // 等于号这些细节不要忘了
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }



}
