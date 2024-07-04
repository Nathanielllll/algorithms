package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
给定一个二维的矩阵，包含'X'和'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的'O' 用 'X' 填充。

示例:

X X X X
X O O X
X X O X
X O X X
运行你的函数后，矩阵变为：

X X X X
X X X X
X X X X
X O X X
 */
public class Test_130 {
    /*
    这道题我们拿到基本就可以确定是图的 dfs、bfs 遍历的题目了。题目中解释说被包围的区间不会存在于边界上，所以我们会想到边界上的 O 要特殊处理，
    只要把边界上的 O 特殊处理了，那么剩下的 O 替换成 X 就可以了。问题转化为，如何寻找和边界联通的 O，我们需要考虑如下情况。

     */

    // =============dfs==============
    public void solve(char[][] board) {
        solveDfs(board);
    }

    public void solveDfs(char[][] board) {
        int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    if (board[i][j] == 'O') {
                        solveDfs(board, i, j, m, n, directs);
                    }
                }
            }
        }
        //
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void solveDfs(char[][] board, int i, int j, int m, int n, int[][] directs) {
        board[i][j] = '#';
        for (int k = 0; k < 4; k++) {
            int x = i + directs[k][0];
            int y = j + directs[k][1];
            if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                solveDfs(board, x, y, m, n, directs);
            }
        }
    }

    // =============bfs==============
    private void solveBfs(char[][] board) {
        int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        Queue<int[]> queue = new LinkedList<>();
        int m = board.length;
        int n = board[0].length;
        // 处理边界的'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    if (board[i][j] == 'O') {
                        queue.add(new int[]{i, j});
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] treasure = queue.poll();
            int row = treasure[0];
            int col = treasure[1];
            board[row][col] = '#';
            for (int j = 0; j < 4; j++) {
                int x = row + directs[j][0];
                int y = col + directs[j][1];
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                    queue.add(new int[]{x, y});
                }
            }
        }
        //
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    // =============unionFind==============
    public void solve_3(char[][] board) {
        int[][] directs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        if (board == null || board.length == 0) return;
        int rows = board.length;
        int cols = board[0].length;

        // 用一个虚拟节点, 边界上的O 的父节点都是这个虚拟节点
        Djset djset = new Djset(rows * cols + 1);
        int dummyNode = rows * cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    boolean isEdge = i == 0 || j == 0 || i == rows - 1 || j == cols - 1;
                    if (isEdge) {
                        djset.union(node(i, j, cols), dummyNode);
                    } else {
                        for (int k = 0; k < 4; k++) {
                            int nextRow = i + directs[k][0];
                            int nextCol = j + directs[k][1];
                            if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                                    && board[nextRow][nextCol] == 'O') {
                                djset.union(node(i, j, cols), node(nextRow, nextCol, cols));
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(board[i][j] == 'O' && !djset.isConnected(node(i, j, cols), dummyNode)){
                    board[i][j] = 'X';
                }
            }
        }
    }

    int node(int i, int j, int cols) {
        return i * cols + j;
    }

    class Djset {
        private int[] parent;
        private int[] rank;

        public Djset(int length) {
            this.parent = new int[length];
            for (int i = 0; i < length; i++) {
                parent[i] = i;
            }
            this.rank = new int[length];
        }

        public int findRoot(int x) {
            if (x != parent[x]) {
                parent[x] = findRoot(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int x_root = findRoot(x);
            int y_root = findRoot(y);

            if (x_root == y_root) {
                return;
            }
            if (rank[x_root] < rank[y_root]) {
                parent[x_root] = y_root;
            } else if (rank[y_root] < rank[x_root]) {
                parent[y_root] = x_root;
            } else {
                parent[x_root] = y_root;
                rank[y_root]++;
            }
        }

        boolean isConnected(int node1, int node2) {
            return findRoot(node1) == findRoot(node2);
        }
    }
}
