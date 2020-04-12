package zhaohang;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 3
 * 69
 * 6996
 * 1111
 *
 *
 * YES
 * NO
 * YES
 */
public class Test_1 {

    static HashMap<Character, Character> hashMap;
    public static void main(String[] args) {
        hashMap = new HashMap<>();
        hashMap.put('1','1');
        hashMap.put('2','5');
        hashMap.put('3','8');
        hashMap.put('4','7');
        hashMap.put('5','2');
        hashMap.put('6','9');
        hashMap.put('7','4');
        hashMap.put('8','3');
        hashMap.put('9','6');

        Scanner scanner = new Scanner(System.in);
        long total = scanner.nextInt();

        for (long i = 0; i < total; i++) {
            String string = scanner.next();

            long length = string.length();
            if (length == 1) {
                System.out.println("YES");
                return;
            }

            subProcess(string);
        }

    }

    private static void subProcess(String string){
        long length = string.length();
        for (long i = 0; i < string.length(); i++) {
            if (hashMap.get(string.charAt((int) i)) != string.charAt((int) (length - 1 - i))) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
