package tencent;

import java.util.Scanner;
import java.util.Stack;

/**
 * 小Q在周末的时候和他的小伙伴来到大城市逛街，一条步行街上有很多高楼，共有n座高楼排成一行。
 * 小Q从第一栋一直走到了最后一栋，小Q从来都没有见到这么多的楼，所以他想知道他在每栋楼的位置处能看到多少栋楼呢？
 * （当前面的楼的高度大于等于后面的楼时，后面的楼将被挡住）
 */
public class Test_2 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int[] arr = new int[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = scanner.nextInt();
//        }


        int[] arr = {5, 3, 8, 3, 2, 5};
        //3 3 5 4 4 4
        int[] ans = getAns(arr);
        for (int i = 0; i < ans.length; i++) {
            if (i != ans.length - 1) {
                System.out.print(ans[i] + " ");
            } else {
                System.out.println(ans[i]);
            }
        }

    }

    // 单调栈
//    单调栈的作用：
//    可以以 O(1) 的时间复杂度得知某个位置左右两侧比他大（或小）的数的位置，
//    当你需要高效率获取某个位置左右两侧比他大（或小）的数的位置的的时候就可以用到单调栈。

    //单调递减栈，1.求arr[i]右边第一个比它大的数；2.求arr[i]左边递减的数的数量
    public static int[] getAns(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            //单调递减的数的数量，而且必然有arr[i] > arr[stack.peek()]
            //这些都能被i看到，因此会直接加上stack.size()
            ans[i] += stack.size();
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            stack.push(i);
        }
        stack.clear();
        //此时ans = [0, 1, 2, 1, 2, 3]       5 3 8 3 2 5逆序变成5 2 3 8 3 5
        for (int i = arr.length - 1; i >= 0; i--) {
            ans[i] += stack.size();
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }
            stack.push(i);
        }
        // 此时ans = [2, 2, 4, 3, 3, 3]       5 3 8 3 2 5
        for (int i = 0; i < arr.length; i++) {
            ans[i]++;
        }
        // 此时ans=[3, 3, 5, 4, 4, 4]
        return ans;
    }

}
