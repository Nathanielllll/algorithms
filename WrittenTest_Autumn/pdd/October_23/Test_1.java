package pdd.October_23;

import java.util.Scanner;

public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int cur = scanner.nextInt();
            min = Math.min(min, cur);
            max = Math.max(max, cur);
        }


        if (max - min > 2 * M) {
            System.out.print(-1);
            System.out.print(" ");
            System.out.print(-1);
        } else {
            int min_price = max - M;
            int max_price = min + M;
            if (min_price < 0) {
                min_price = 0;
            }
            System.out.print(min_price);
            System.out.print(" ");
            System.out.print(max_price);
        }
    }
}
