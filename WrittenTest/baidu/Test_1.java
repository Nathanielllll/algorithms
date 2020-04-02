package baidu;

import java.util.Scanner;

public class Test_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long result = (n - 1) * n - 1;
        System.out.println(result);
    }

}
