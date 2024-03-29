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
    private int[] dx = {0, 0, -1, 1};
    private int[] dy = {1, -1, 0, 0};

    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean isEdge = i == 0 || j == 0 || i == rows - 1 || j == cols - 1;
                if (isEdge) {
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        // 只对当前的位置进行判断！！bfs也是一样的
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }

        board[i][j] = '#';
        for (int k = 0; k < 4; k++) {
            dfs(board, i + dx[k], j + dy[k]);
        }
    }

    private class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // =============bfs==============
    public void solve_1(char[][] board) {
        if (board == null || board.length == 0) return;
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean isEdge = i == 0 || j == 0 || i == rows - 1 || j == cols - 1;
                if (isEdge) {
                    bfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void bfs(char[][] board, int i, int j) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j));
        while (!queue.isEmpty()) {
            Point temp = queue.poll();
            if (temp.row >= 0 && temp.col >= 0 && temp.row < board.length && temp.col < board[0].length
                    && board[temp.row][temp.col] == 'O') {
                board[temp.row][temp.col] = '#';
                for (int k = 0; k < 4; k++) {
                    queue.add(new Point(temp.row + dx[k], temp.col + dy[k]));
                }
            }
        }
    }

    // =============unionFind==============
    public void solve_3(char[][] board) {
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
                            int nextRow = i + dx[k];
                            int nextCol = j + dy[k];
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
