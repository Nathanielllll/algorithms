package netEase.packet3;

import java.util.Scanner;

/**
 * 定义S(n)，表示n在十进制下的各位数字和。
 * 现在给定一个x,请你求出最小正整数n，满足x<=S(n).
 */
public class Test_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        for (int i = 0; i < total; i++) {
            int value = scanner.nextInt();
            System.out.println(subProcess(value));
        }
    }


    public static String subProcess(long value){
        long times = value / 9;
        long rest = value % 9;

        StringBuffer stringBuffer = new StringBuffer();
        if (rest != 0) {
            stringBuffer.append(rest);
        }
        for (long i = 0; i < times; i++) {
            stringBuffer.append(9);
        }
        return stringBuffer.toString();
    }
}
