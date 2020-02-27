package dp;

public class Code_10 {
    public static long Fibonacci_1(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return Fibonacci_1(n - 1) + Fibonacci_1(n - 2);
    }

    public static long Fibonacci_2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        long current = 0;
        long pre = 1;
        long prePre = 1;
        for (int i = 3; i <= n; i++) {
            current = pre + prePre;
            prePre = pre;
            pre = current;
        }
        return current;
    }

}
