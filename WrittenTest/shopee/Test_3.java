package shopee;

import java.util.Scanner;

/**
 * @author luzhi
 * @date 2021/4/21
 */
public class Test_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int i = 0;
        for (; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (('0' <= ch && ch <= '9') || ch == '-') {
                break;
            }
        }

        String subString = str.substring(i);
        int n = Integer.parseInt(subString);
        System.out.println("f(n) = " + climbStairs(n));
    }

    public static long climbStairs(int n) {
        if (n <= 0) {
            return 0;
        }
        long a = 1;
        long b = 1;
        for (int i = 2; i <= n; i++) {
            long tmp = a;
            a = b;
            b = tmp + b;
        }
        return b;
    }
}
