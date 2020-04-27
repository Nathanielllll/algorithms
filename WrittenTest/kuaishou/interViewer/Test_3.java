package kuaishou.interViewer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Test_3 {
    public static void main(String[] args) {
        //[8,9,7],[5,8,3]
        int[] a = {8, 9, 7};
        int[] b = {5, 8, 3};
        int[] res = WaitInLine(a, b);
        System.out.println(Arrays.toString(res));
    }

    static List<Integer> list;
    static int minSatisfied;

    public static int[] WaitInLine(int[] a, int[] b) {
//    public int[] WaitInLine (int[] a, int[] b) {
        // write code here
        int len = a.length;
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = i + 1;
        }

        boolean[] used = new boolean[len];
        Stack<Integer> stack = new Stack<>();

        list = new LinkedList<>();
        minSatisfied = Integer.MAX_VALUE;

        //list->int[]
        backTrack(nums, used, stack, a, b);
        int[] res = new int[len];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }


    public static void backTrack(int[] nums, boolean[] used, Stack<Integer> stack, int[] a, int[] b) {
        if (stack.size() == nums.length) {
            int satisfied = 0;
            for (int i = 0; i < stack.size(); i++) {
                int num = stack.get(i);
                satisfied += a[num - 1] * i + b[num - 1] * (nums.length - i - 1);
            }
            if (minSatisfied > satisfied) {
                minSatisfied = satisfied;
                list = new LinkedList<>(stack);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                stack.push(nums[i]);

                backTrack(nums, used, stack, a, b);

                stack.pop();
                used[i] = false;
            }
        }
    }
}
