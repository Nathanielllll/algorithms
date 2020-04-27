package alibaba.April_03;


/**
 * 找出数组中有价值的数的个数，有价值的数定义为：该数左边有比他大的数，找出最小值，右边有比他小的数，找出最大值，而且这两个值能成倍数关系。
 * 输入有两行，第一行是数组长度n，第二行是数组a中每个数的值，数组长度的限制为小于1000，数组元素的值限制为小于10^18
 * 输出是有价值的数的个数
 * 例如：
 * 输入
 * 3
 * 4 3 2
 * 输出
 * 1
 * <p>
 * 思路：先对数组排序，再使用单调栈。
 * 例如，一个数组：
 * 数组索引： 0    1     2   3    4     5     6
 * 数组的值：200  150   80  100   82    50   90
 * 则带着索引一起，对数组的值排序后，变成：
 * 数组索引： 5   2    4    6     3    1     0
 * 数组的值：50   80   82   90  100   150   200
 * <p>
 * 可以看出排序前某个数左边大数的最小值，就是去排序后它的右边找第一个比它索引小的索引对应的值，
 * 同理，对于排序前某个数右边小数的最大值，就是去排序后它的左边找第一个比它索引大的索引对应的值。可以用单调栈实现。
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

class Pair {
    int index;
    long val;

    Pair(int index, long val) {
        this.index = index;
        this.val = val;
    }
}

public class Test_1 {
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] array = new long[n];//输入的数组
        Pair[] pairs = new Pair[n];//将array数组的索引和数组的值进行封装，组成的数组
        long[] right = new long[n];//记录array数组每个数的右边比它小的数的最大值，若不存在则先标记为-1
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextLong();
            pairs[i] = new Pair(i, array[i]);
        }
        Arrays.sort(pairs, new Comparator<Pair>() {
            //对array数组的值进行排序
            @Override
            public int compare(Pair o1, Pair o2) {
                return (int) (o1.val - o2.val);
            }
        });

        getValue(pairs, right, array);
        System.out.println(cnt);
    }

    public static void getValue(Pair[] pairs, long[] right, long[] array) {
        Stack<Integer> stack1 = new Stack<>();
        //找出数组中该数右边比它小的数的最大值，即对排序后的pair数组去找单调栈中左边第一个比它索引大的索引所对应的值
        for (int i = 0; i < pairs.length; i++) {
            while (!stack1.isEmpty() && pairs[i].index > stack1.peek()) {
                stack1.pop();
            }
            if (!stack1.isEmpty()) {
                right[pairs[i].index] = array[stack1.peek()];
            } else {//不存在这样的值，标记为-1；肯定不会是有价值的数
                right[pairs[i].index] = -1;
            }
            stack1.push(pairs[i].index);
        }

        long left;
        Stack<Integer> stack2 = new Stack<>();
        //找出数组中该数左边比它大的数的最小值，即对排序后的pair数组去找单调栈中右边第一个比它索引小的索引所对应的值
        for (int i = pairs.length - 1; i >= 0; i--) {
            while (!stack2.isEmpty() && pairs[i].index < stack2.peek()) {
                stack2.pop();
            }
            if (!stack2.isEmpty()) {
                left = array[stack2.peek()];
                if (right[pairs[i].index] != -1) {
                    if (left % right[pairs[i].index] == 0) {
                        cnt++;
                    }
                }
            }
            stack2.push(pairs[i].index);
        }

    }
}
