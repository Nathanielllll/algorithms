package alibaba.March_25;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出一个二维矩阵，这个矩阵的每一行和每一列都是一个独立的等差数列，其中一些数据缺失了，现在需要推理隐藏但是可以被唯一确定的数字，然后对输入的查询进行回答。
 * <p>
 * 输入描述：
 * 第一行，n,m,q分别表示矩阵的行数，列数和查询的条数。
 * 接下来的n行，每行m个数表示这个矩阵，0表示缺失数据。
 * 接下来q行，每行两个数字i,j表示对矩阵中第i行第j列的数字进行查询。
 * <p>
 * 输出描述：
 * 如果可以确定该位置的数字，则输出数字，如果不能确定则输出UNKNOWN。
 * <p>
 * 输入示例：
 * 2 3 6
 * 1 0 3
 * 0 0 0
 * 1 1
 * 1 2
 * 1 3
 * 2 1
 * 2 2
 * 2 3
 * <p>
 * 输出示例：
 * 1
 * 2
 * 3
 * Unknown
 * Unknown
 * Unknown
 */
public class Test_2 {
    public static void main(String[] args) {
        int[][] nums = {{1,0,3},{0,0,0}};

        process(nums, 1,1);
        process(nums, 1,2);
        process(nums, 1,3);
        process(nums, 2,1);
        process(nums, 2,2);
        process(nums, 2,3);
    }


    public static void process(int[][] nums, int row, int col) {
        int rows = nums.length;
        int cols = nums[0].length;

        int[] rowGap = new int[rows];
        int[] colGap = new int[cols];
        int[] rowNum = new int[rows];
        int[] colNum = new int[cols];

        for (int i = 0; i < rows; i++) {
            int first_col = -1;
            for (int j = 0; j < cols; j++) {
                if (nums[i][j] != 0) {
                    if (first_col == -1) {
                        first_col = j;
                    } else {
                        int gap = (nums[i][j] - nums[i][first_col]) / (j - first_col);
                        rowGap[i] = gap;
                        int num = nums[i][j] - gap * j;
                        rowNum[i] = num;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (nums[i][j] == 0 && rowGap[i] != 0){
                    nums[i][j] = rowNum[i] + rowGap[i] * j;
                }
            }
        }

        for (int j = 0; j < cols; j++) {
            int first_row = -1;
            for (int i = 0; i < rows; i++) {
                if (nums[i][j] != 0) {
                    if (first_row == -1) {
                        first_row = i;
                    }else {
                        int gap = (nums[i][j] - nums[first_row][j]) / (i - first_row);
                        colGap[j] = gap;
                        break;
                    }
                }
            }
        }

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                if(nums[i][j] == 0 && colGap[i] != 0){
                    nums[i][j] = colNum[j] + colNum[j] * i;
                }
            }
        }

        if (nums[row - 1][col - 1] == 0) {
            System.out.println("Unkown");
        }else {
            System.out.println(nums[row - 1][col - 1]);
        }

    }
}
