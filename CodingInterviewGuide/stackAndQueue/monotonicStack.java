package stackAndQueue;

import java.util.Arrays;
import java.util.Stack;

/*
单调栈
不重复数组，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置
 */
public class monotonicStack {
    public static int[] getRightMin(int[] arr){
        int[] res = new int[arr.length];
        Arrays.fill(res, -1);

        Stack<Integer> stack = new Stack<>();

        //右边离i位置最近的，且比arr[i]小，使用单调递增栈
        for (int i = 0; i < arr.length; i++) {
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int index = stack.pop();
                res[index] = i;
            }
            stack.push(i);
        }
        return res;
    }
    public static int[] getLeftMin(int[] arr){
        int[] res = new int[arr.length];
        Arrays.fill(res, -1);

        Stack<Integer> stack = new Stack<>();

        //比arr[i]小，使用单调递增栈
        for (int i = arr.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int index = stack.pop();
                res[index] = i;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3,4,1,5,6,2,7};
        int[] res = getLeftMin(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

}
