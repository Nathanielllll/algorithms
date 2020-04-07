package meituan;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test_1 {
    /**
     * 题目描述：
     * 给出一个序列包含n个正整数的序列A，你可以从中删除若干个数，使得剩下的数字中的最大值和最小值之差不超过x，请问最少删除多少个数字。
     *
     * 输入第一行仅包含两个正整数n和x，表示给出的序列的长度和给定的正整数。(1<=n<=1000,1<=x<=10000)
     *
     * 接下来一行有n个正整数，即这个序列，中间用空格隔开。(1<=a_i<=10000)
     * 5 2
     * 2 1 3 2 5
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        if (n == 1) {
            System.out.println(0);
            return;
        }

        List<Integer> list = new LinkedList<>();
        List<Integer> list_copy = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            list.add(value);
            list_copy.add(value);
        }

        Collections.sort(list);//从小到大
        Collections.sort(list_copy, Collections.reverseOrder());//从大到小

        int count = 0;
        while (list_copy.get(0) - list.get(0) > x) {
            if (list_copy.get(0) - list_copy.get(1) > list.get(1) - list.get(0)) {
                list_copy.remove(0);
                list.remove(list_copy.size() - 1);
                count++;
            }else {

                //删除xiao的数
                list.remove(0);
                list_copy.remove(list.size() - 1);
                count++;
            }

        }
        System.out.println(count);
    }
}
