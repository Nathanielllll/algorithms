package netEase.packet4;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 小易有一个长度为n的数字数组a_1, a_2, …, a_n。
 *
 * 问你是否能用这n个数字构成一个环(首尾连接)，
 * 使得环中的每一个数字都小于它相邻的两个数字的和(每个数字都必须使用并且每个数字只能使用一次)。
 * 1
 * 5
 * 17 6 17 11 17
 *
 * 因为每个数字小于相邻只和，直接排序数组，
 * 然后只要操作最大数左右两边之和大于最大数，此时只需要将最大数和第二大数交换位置即可。
 */
public class Test_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long nums = sc.nextLong();
        for (int i = 0; i < nums ; i++) {
            long count =  sc.nextLong();
            long[] numbers = new long[(int)count];
            for (int j = 0; j < count ; j++) {
                numbers[j] = sc.nextLong();
            }
            Arrays.sort(numbers);
            //最大数左右两边之和大于最大数
            boolean flag = numbers[(int)count-1] < numbers[0] + numbers[(int)count-2];
            if (!flag){
                if (count == 3){
                    System.out.println("NO");
                }else {
                    boolean a = numbers[(int)count-1]  < numbers[(int)count-3] + numbers[(int)count-2];
                    if (a) {
                        System.out.println("YES");
                    }else {
                        System.out.println("NO");
                    }
                }
            }else {
                System.out.println("YES");
            }
        }
    }
}
