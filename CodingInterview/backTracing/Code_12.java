package backTracing;

/**
 * 矩阵中的路径
 *
 * 1.结束的条件 2.进入某个点的条件
 */
public class Code_12 {

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || str == null || matrix.length <= 0 || matrix.length < str.length) {
            return false;
        }
        boolean[] visited = new boolean[rows * cols];
        int curLength = 0;

        // 以每一个点为起始进行搜索
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (coreHasPath(matrix, rows, cols, i, j, str, visited, curLength)) {
                    return true;
                }
            }
        }
        return false;
    }

    //需要有记录是否进入此点过的矩阵、需要记录走过的长度
    /***
     * 辅助函数
     * @param matrix
     * @param rows
     * @param cols
     * @param row
     * @param col
     * @param str 需要走的路径
     * @param visited 记录是否已经访问过的矩阵
     * @param curLength
     * @return
     */
    private static boolean coreHasPath(char[] matrix, int rows, int cols, int row, int col,
                                       char[] str, boolean[] visited, int curLength) {
        //结束的条件
        if (curLength == str.length) {
            return true;
        }
        boolean hasPath = false;
        //进入并查看此点的条件
        if (row >= 0 && row < rows && col >= 0 && col < cols &&
                !visited[row * cols + col] && matrix[row * cols + col] == str[curLength]) {
            curLength++;
            visited[row * cols + col] = true;
            hasPath = coreHasPath(matrix, rows, cols, row - 1, col, str, visited, curLength) ||
                    coreHasPath(matrix, rows, cols, row + 1, col, str, visited, curLength) ||
                    coreHasPath(matrix, rows, cols, row, col - 1, str, visited, curLength) ||
                    coreHasPath(matrix, rows, cols, row, col + 1, str, visited, curLength);
//            if (!hasPath) {
//                curLength--;
//                visited[row * cols + col] = false;
//            }
            curLength--;
            visited[row * cols + col] = false;
        }
        return hasPath;
    }

    public static void test1() {
        char[] matrix = "ABTGCFCSJDEH".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = "BFCTB".toCharArray();
        if (hasPath(matrix, rows, cols, str)) {
            System.out.println("Test1 passed.");
        } else {
            System.out.println("Test1 failed.");
        }
    }

    public static void test2() {
        char[] matrix = "ABTGCFCSJDEH".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = "BFCE".toCharArray();
        if (hasPath(matrix, rows, cols, str))
            System.out.println("Test2 passed.");
        else
            System.out.println("Test2 failed.");
    }

    public static void test7() {
        char[] matrix = "AAAAAAAAAAAA".toCharArray();
        int rows = 3;
        int cols = 4;
        char[] str = "AAAAAAAAAAAA".toCharArray();
        if (hasPath(matrix, rows, cols, str))
            System.out.println("Test7 passed.");
        else
            System.out.println("Test7 failed.");
    }

    public static void main(String[] args) {

        test1();
        test2();
        test7();
    }


}
