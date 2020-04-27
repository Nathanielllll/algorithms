package ByteDance;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 3 4
 * 50 100 200
 * 99 199 200 300
 */
public class Test_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int discountTotal = scanner.nextInt();
        int itemTotal = scanner.nextInt();

        long[] Discounts = new long[discountTotal];
        for (int i = 0; i < discountTotal; i++) {
            Discounts[i] = scanner.nextLong();
        }
        Arrays.sort(Discounts);

        long[] Items = new long[itemTotal];
        for (int i = 0; i < itemTotal; i++) {
            Items[i] = scanner.nextLong();
        }
        Arrays.sort(Items);

        long count = 0;
        outer:
        for (int i = itemTotal - 1; i >= 0; i--) {
            for (int j = discountTotal - 1; j >= 0; j--) {
                long itemPrice = Items[i];
                long discount = Discounts[j];
                if(itemPrice >= discount){
                    count += itemPrice - discount;
                    continue outer;
                }
            }
        }

        System.out.println(count);

    }
}
