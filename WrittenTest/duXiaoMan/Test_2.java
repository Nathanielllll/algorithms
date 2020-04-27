//package duXiaoMan;
//
//import java.util.Scanner;
///*
//时间限制：C/C++语言 1000MS；其他语言 3000MS
//内存限制：C/C++语言 65536KB；其他语言 589824KB
//题目描述：
//西西所在的国家有N座城市，每座城市都有一道传送门，城市 i 的传送门通往城市 a[i]。当西西位于城市 i 时，每次他可以执行以下三种操作中的一种：
//
//  花费 A 的费用，从城市 i 前往城市 a[i]；
//
//  如果 a[i] > 1，可以花费 B 的费用，将 a[i] 的值减少 1；
//
//  如果 a[i] < N，可以花费 C 的费用，将 a[i] 的值增加 1。
//
//现在，西西想从城市 1 前往城市 N，那么他至少要花费多少费用？
// */
//public class Test_2 {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int total = scanner.nextInt();
//        int A = scanner.nextInt();
//        int B = scanner.nextInt();
//        int C = scanner.nextInt();
//
//        int[] nums = new int[total];
//        for (int t = 0; t < total; t++) {
//            nums[t] = scanner.nextInt();
//        }
//
//        //初始化，dp为
//        int[][] dp = new int[total][3];
//        for (int j = 0; j < 3; j++) {
//            for (int i = 0; i < total; i++) {
//                dp[i][j] = nums[i];
//            }
//        }
//
//        for (int i = 0; i < total; i++) {
//            int min = Math.min(dp[i-1][0],Math.min(dp[i-1][1],dp[i-1][2]));
//            for (int j = 0; j < 3; j++) {
//                dp[i][j] = dp[i - 1][]
//            }
//        }
//
//
//    }
//}
