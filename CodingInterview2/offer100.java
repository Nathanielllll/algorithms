import java.util.List;

public class offer100 {
    public static int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[] dp = new int[rows + 1];
        for (int i = rows - 1; i >= 0; i--) {
            int cols = triangle.get(i).size();
            for (int j = 0; j < cols; j++) {
                int value = triangle.get(i).get(j);
                dp[j] = Math.min(dp[j], dp[j + 1]) + value;
            }
        }
        return dp[0];
    }
}
