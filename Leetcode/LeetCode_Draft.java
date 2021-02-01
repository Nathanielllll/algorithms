import tree.Test_124;
import tree.Test_530;
import tree.Test_95;

import java.util.*;

public class LeetCode_Draft {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


//    public int maxPathSum(TreeNode root) {
//
//
//    }

//    public int numSimilarGroups(String[] strs) {
//
//    }

    private int result;
    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;

        helper(root);
        return result;
    }

    private int helper(TreeNode root){
        if(root == null) return 0;

        int left = Math.max(0, helper(root.left));
        int right = Math.max(0,  helper(root.right));

        int sum = root.val + left + right;
        result = Math.max(result, sum);
        return sum;
    }











































    class UnionFind {
        private int[] parents;
        private int[] rank;

        public UnionFind(int length) {
            this.parents = new int[length];
            for (int i = 0; i < length; i++) {
                parents[i] = i;
            }
            this.rank = new int[length];
        }

        public boolean isSameRoot(int x, int y) {
            return find(x) == find(y);
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public boolean union(int x, int y) {
            int x_root = find(x);
            int y_root = find(y);
            if (x_root == y_root) {
                return false;
            } else {
                if (rank[x_root] < rank[y_root]) {
                    parents[x_root] = y_root;
                } else if (rank[x_root] > rank[y_root]) {
                    parents[y_root] = x_root;
                } else {
                    parents[x_root] = y_root;
                    rank[y_root]++;
                }
                return true;
            }
        }
    }

//    public static void main(String[] args) {
//        LeetCode_Draft test = new LeetCode_Draft();
//        int[][] grid = {{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}};
//        System.out.println(test.swimInWater(grid));
//    }

    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    private int rows;
    private int cols;

    public int swimInWater(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        // int[]{value, row, col}
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                priorityQueue.add(new int[]{grid[row][col], row, col});
            }
        }

        UnionFind unionFind = new UnionFind(rows * cols + 1);
        for (int i = 0; i < rows * cols; i++) {
            int[] cur = priorityQueue.poll();
            int value = cur[0];
            int row = cur[1];
            int col = cur[2];
            int nodeIndex = nodeIndex(row, col);

            for (int j = 0; j < 4; j++) {
                int nextRow = row + dx[j];
                int nextCol = col + dy[j];
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
                        && value >= grid[nextRow][nextCol]) {
                    int nextNodeIndex = nodeIndex(nextRow, nextCol);
                    unionFind.union(nodeIndex, nextNodeIndex);
                }
            }

            if (unionFind.isSameRoot(0, nodeIndex(rows - 1, cols - 1))) {
                return value;
            }
        }
        return -1;
    }

    private int nodeIndex(int row, int col) {
        return row * cols + col;
    }
}
