package backTracing;

/**
 * 机器人的运动范围
 */
public class Code_13_ATTENTION {

    public static int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0) {
            return 0;
        }

        boolean[] visited = new boolean[rows * cols];

        return movingCountCore(threshold, rows, cols, 0, 0, visited);
    }

    public static int movingCountCore(int threshold, int rows, int cols,
                                      int row, int col, boolean[] visited) {
        int count = 0;
        if (check(threshold, rows, cols, row, col, visited)) {
            visited[row * cols + col] = true;
            count = 1 +
                    movingCountCore(threshold, rows, cols, row + 1, col, visited) +
                    movingCountCore(threshold, rows, cols, row - 1, col, visited) +
                    movingCountCore(threshold, rows, cols, row, col + 1, visited) +
                    movingCountCore(threshold, rows, cols, row, col - 1, visited);
            /**一定注意没有下面这个条件！！*/
            //visited[row * cols + col] = false;
        }
        return count;
    }

    /**
     * 检查是否符合条件，能不能进入此格子
     *
     * @param threshold
     * @param rows
     * @param cols
     * @param visited
     * @param row
     * @param col
     * @return
     */
    public static boolean check(int threshold, int rows, int cols,
                                int row, int col, boolean[] visited) {
        if (row >= 0 && row < rows &&
                col >= 0 && col < cols &&
                !visited[row * cols + col] &&
                getDigitSum(row) + getDigitSum(col) <= threshold) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 一个数字的位数之和
     *
     * @param number
     * @return
     */
    public static int getDigitSum(int number) {
        int result = 0;
        while (number != 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(1, 2, 1) + "[2]");
        System.out.println(movingCount(15, 20, 20) + "[359]");
        System.out.println(movingCount(10, 1, 100) + "[29]");
        System.out.println(movingCount(10, 1, 10) + "[10]");
        System.out.println(movingCount(15, 100, 1) + "[79]");
        System.out.println(movingCount(15, 10, 1) + "[10]");
        System.out.println(movingCount(5, 10, 10) + "[21]");
        System.out.println(movingCount(12, 1, 1) + "[1]");
        System.out.println(movingCount(17, 3, 2) + "[6]");
    }
}
