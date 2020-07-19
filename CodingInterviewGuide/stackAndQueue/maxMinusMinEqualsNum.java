package stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

/*
给定数组arr和整数num，共返回有多少个子数组满足：
max(arr[i..j])-min(arr[i..j])<=num

有两个结论：
1.如果子数组arr[i..j]满足条件，则arr[i..j]中的每一个子数组arr[k..l]都满足条件。
2.如果子数组arr[i..j]不满足条件，则所有包含arr[i..j]的子数组，都不满足条件。
 */
public class maxMinusMinEqualsNum {
    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 1, 3, 5, 6, 7, 8, 9};
        int num = 3;
        System.out.println(getNum(arr, num));
    }

    public static int getNum(int[] arr, int num) {
        //和滑动窗口的思想一样！
        Deque<Integer> dequeMax = new LinkedList<>();
        Deque<Integer> dequeMin = new LinkedList<>();

        int res = 0;
        int length = arr.length;
        int i = 0, j = 0;//滑动窗口的左右边界

        while (i < length) {
            while (j < length) {
                if (dequeMin.isEmpty() || dequeMin.getLast() != j) {
                    while(!dequeMin.isEmpty() && arr[dequeMin.getLast()] >= arr[j]){
                        dequeMin.pollLast();
                    }
                    dequeMin.addLast(j);

                    while(!dequeMax.isEmpty() && arr[dequeMax.getLast()] <= arr[j]){
                        dequeMax.pollLast();
                    }
                    dequeMax.addLast(j);
                }
                if (arr[dequeMax.getFirst()] - arr[dequeMin.getFirst()] > num) {
                    break;
                }
                j++;
            }
            res += j - i;

            if (dequeMin.getFirst() == i) {
                dequeMin.pollFirst();
            }
            if (dequeMax.getFirst() == i) {
                dequeMax.pollFirst();
            }
            i++;
        }
        return res;

    }
}
