package shopee;

import java.util.Scanner;

/**
 * @author luzhi
 * @date 2021/4/21
 */
public class Test_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.nextLine();
            String[] strings = str.split(" ");
            if (isContain(strings[0], strings[1])) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
    }

    public static boolean isContain(String str1, String str2) {
        return str1.contains(str2) || str2.contains(str1);
    }
}
