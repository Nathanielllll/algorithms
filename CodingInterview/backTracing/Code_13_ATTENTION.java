package backTracing;

/**
 * 机器人的运动范围
 */
public class Code_13_ATTENTION {
    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return helper(0, 0, m, n, visited, k);
    }

    private int helper(int i, int j, int row, int col, boolean[][] visited, int k) {
        int count = 0;

        // 满足条件：说明这个点是可以进入的
        if (i >= 0 && i < row && j >= 0 && j < col
                && !visited[i][j] && require(i, j, k)) {
            visited[i][j] = true;
            count = 1 // 这个点是可以进入的，则在count上加上这个点的1。
                    // 下面的是尝试这个点的上下左右点能否进入。
                    + helper(i - 1, j, row, col, visited, k)
                    + helper(i + 1, j, row, col, visited, k)
                    + helper(i, j - 1, row, col, visited, k)
                    + helper(i, j + 1, row, col, visited, k);
        }

        return count;
    }

    private boolean require(int i, int j, int k) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum <= k;
    }
}
