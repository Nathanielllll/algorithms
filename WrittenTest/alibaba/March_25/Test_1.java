package alibaba.March_25;

/**
 * 大概题意：给一个3*n的数组，从每列中取出一个数，按序排列（这里当成新的数组p好了），求相邻两个数的绝对值的和的最小值。
 */
public class Test_1 {
    public static void main(String[] args) {
        int[][] arr = {{5,9,5,4,4},{4,7,4,10,3},{2,10,9,2,3}};
        System.out.println(subProcess(arr));
    }

    public static int subProcess(int[][] arr){
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] dp = new int[rows][cols];

        for (int col = 1; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                //dp[0][col - 1]表示上一个数字选的是arr[0][col - 1]
                int res0 = dp[0][col - 1] + Math.abs(arr[row][col] - arr[0][col - 1]);
                int res1 = dp[1][col - 1] + Math.abs(arr[row][col] - arr[1][col - 1]);
                int res2 = dp[2][col - 1] + Math.abs(arr[row][col] - arr[2][col - 1]);
                int min = Math.min(res0, Math.min(res1, res2));
                dp[row][col] = min;
            }
        }
        return Math.min(Math.min(dp[0][cols-1],dp[1][cols-1]), dp[2][cols-1]);
    }
}
