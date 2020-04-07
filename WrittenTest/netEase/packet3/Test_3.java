package netEase.packet3;

import java.util.Scanner;

/**
 * 小易给定一个到的排列，希望你能求出这个序列中所有逆序对的距离和。
 * <p>
 * 5
 * 1 3 6 2 5
 * <p>
 * 3
 * <p>
 * 逆序对:
 * (3, 2)距离为2
 * (4, 2)距离为1
 * 总和为3
 */
public class Test_3 {

    static int count;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if(data[i] - data[j] > 0){
                    count+=j-i;
                }
            }
        }
        System.out.println(count);



    }

}

