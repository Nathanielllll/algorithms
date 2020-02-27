package search;

public class Code_4 {
    public static boolean find(int[][] matrix, int number) {
        if (matrix == null || matrix.length < 1) {
            return false;
        }

        //左下角开始的坐标
        int row = matrix.length - 1;
        int column = 0;
        while (row >= 0 && column <= matrix[0].length - 1) {
            if (number > matrix[row][column]) {
                column++;
            } else if (number < matrix[row][column]) {
                row--;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        System.out.println(find(matrix, 7));    // 要查找的数在数组中
        System.out.println(find(matrix, 5));    // 要查找的数不在数组中
        System.out.println(find(matrix, 1));    // 要查找的数是数组中最小的数字
        System.out.println(find(matrix, 15));   // 要查找的数是数组中最大的数字
        System.out.println(find(matrix, 0));    // 要查找的数比数组中最小的数字还小
        System.out.println(find(matrix, 16));   // 要查找的数比数组中最大的数字还大
        System.out.println(find(null, 16));     // 健壮性测试，输入空指针
    }
}
