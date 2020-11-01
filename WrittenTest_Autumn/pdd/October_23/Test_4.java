//package pdd.October_23;
//
//public class Test_4 {
//    public static void main(String[] args) {
//
//    }
//
//    private static int process(int m, int n, double in, double out) {
//        int[][] dp = new int[m + 1][n + 1];
//
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (j == 1) {
//                    dp[i][j] = dp[i-1][]
//                } else if (j == n) {
//
//                } else {
//
//                }
//                dp[i][j] =
//            }
//        }
//    }
//
//    // x个人出的概率
//    private static double x_out(int x, double out) {
//        return Math.pow(out, x);
//    }
//
//    // 没有人出的概率，x表示最多x人出去
//    private static double not_out(int x, double out) {
//        if (x == 0) {
//            return 0;
//        }
//
//        double sum = 0;
//        for (int i = 1; i <= x; i++) {
//            sum += Math.pow(out, x);
//        }
//        return 1 - sum;
//    }
//
//    private static double not_in(double in){
//        return 1 - in;
//    }
//}
