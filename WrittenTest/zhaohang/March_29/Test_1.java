package zhaohang.March_29;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        List<List<Integer>> triangle = new LinkedList<>();

        for (int i = 1; i <= total; i++) {
            List<Integer> list = new LinkedList<>();
            for (int j = 1; j <= i; j++) {
                list.add(scanner.nextInt());
            }

            triangle.add(list);
        }
        System.out.println(minimumTotal(triangle));
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();

        int[] dp = new int[rows + 1];
        for (int row = rows - 1; row >= 0; row--) {
            int cols = triangle.get(row).size();
            for (int col = 0; col < cols; col++) {
                int value = triangle.get(row).get(col);
                dp[col] = Math.max(dp[col], dp[col + 1]) + value;
            }
        }
        return dp[0];
    }
}
