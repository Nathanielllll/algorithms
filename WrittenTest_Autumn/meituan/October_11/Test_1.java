package meituan.October_11;

import java.util.Scanner;

public class Test_1 {
    /*
    小团一天最多烤n个蛋糕，每个蛋糕有一个正整数的重量。
    早上，糕点铺已经做好了m个蛋糕。
    有一个顾客要来买两个蛋糕，他希望买这一天糕点铺中最重和最轻的蛋糕，并且希望这两个蛋糕的重量恰好为a和b。
    剩余的n-m个蛋糕可以现烤，请问小团能否满足他的要求？
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            String[] strings_1 = line1.split(" ");
            String[] strings_2 = line2.split(" ");

            int[] nums_1 = new int[strings_1.length];
            for (int i = 0; i < nums_1.length; i++) {
                nums_1[i] = Integer.parseInt(strings_1[i]);
            }
            int n = nums_1[0];
            int m = nums_1[1];
            int a = nums_1[2];
            int b = nums_1[3];

            int[] nums_2 = new int[strings_2.length];
            for (int i = 0; i < nums_2.length; i++) {
                nums_2[i] = Integer.parseInt(strings_2[i]);
            }

            String ans = process(n, m, a, b, nums_2);
            System.out.println(ans);
        }
    }

    private static String process(int n, int m, int a, int b, int[] nums_2){
        // 确保a>=b
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        int more_count = 0;
        int less_count = 0;
        for (int i = 0; i < nums_2.length; i++) {
            int num = nums_2[i];
            if (num > a || num < b) {
                return "NO";
            }else {
                if(num == a && more_count == 0){
                    more_count++;
                }
                if(num == b && less_count == 0){
                    less_count++;
                }
            }
        }

        if(2 - (more_count + less_count) <= n - m){
            return "YES";
        }else {
            return "NO";
        }
    }
}
