package stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

public class getMaxWindow {
    public static int[] getMaxWindow(int[] arr, int k) {
        if (arr == null || arr.length < 1 || k < 1 || arr.length < k) {
            return new int[0];
        }
        if (arr.length == 1) {
            return arr;
        }

        Deque<Integer> queue = new LinkedList<>();
        int[] res = new int[arr.length - k + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            if (!queue.isEmpty()) {
                if (i-k==queue.peek()) {
                    queue.pop();
                }
                while (!queue.isEmpty() && num >= arr[queue.getLast()]) {
                    queue.removeLast();
                }
            }

            queue.add(i);

            if (i >= k - 1) {
                res[index++] = arr[queue.peek()];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,1,2,0,5};
        int[] res = getMaxWindow(arr, 3);
        for(int num : res){
            System.out.println(num);
        }
    }
}
