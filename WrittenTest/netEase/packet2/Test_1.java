package netEase.packet2;

import java.util.Scanner;

public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //一共多少行
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            long A = scanner.nextLong();
            long B = scanner.nextLong();
            long p = scanner.nextLong();
            long q = scanner.nextLong();
            subProcess(A, B, p, q);
        }
    }

    public static void subProcess(long A, long B, long p, long q){
        int count = 0;
        while (A < B) {
            if (A + p > B) {
                A = A + p;
            } else {
                p = p * q;
            }
            count++;
        }
        System.out.println(count);
    }
}
