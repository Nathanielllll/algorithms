package netEase.April_11;

import java.util.*;


public class Test_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int total = scanner.nextInt();
        for (int t = 0; t < total; t++) {
            int len = scanner.nextInt();
            int[] nums = new int[len];
            int[] nums_sort = new int[len];
            for (int i = 0; i < len; i++) {
                nums[i] = scanner.nextInt();
                nums_sort[i] = nums[i];
            }

            Arrays.sort(nums_sort);

            int[] values = new int[len];
            for (int i = 0; i < len; i++) {
                values[i] = scanner.nextInt();
            }
            res = Integer.MAX_VALUE;
            boolean[] used = new boolean[nums.length];
            Stack<Integer> stack = new Stack<>();
            subProcess(nums_sort, nums, nums.length, values, stack, used);

            System.out.println(res);
        }
    }

//    public static void main(String[] args) {
//        int[] nums = {1,2,3};
//        int[] values = {1,2,3};
//        int length = nums.length;
//        int[] nums_sort = nums.clone();
//        Arrays.sort(nums_sort);
//        Stack<Integer> stack = new Stack<>();
//        boolean[] used = new boolean[length];
//
//        subProcess(nums_sort, nums, length, values, stack, used);
//        System.out.println(res);
//    }


    static int res;
    public static void subProcess(int[] nums_sort, int[] nums, int length, int[] values, Stack<Integer> stack, boolean[] used){
        //开始操作
        if (stack.size() == length) {
            //排序B
            List<Integer> list = new LinkedList<>(stack);
            //不是错排序，就返回
            for (int i = 0; i < length; i++) {
                if(list.get(i) == nums[i]){
                    return;
                }
            }
            //是错排序的情况下：

            //排序A：nums
            //数字i在排序A的位置
            HashMap<Integer, Integer> hashMapA = new HashMap<>();
            for (int i = 0; i < length; i++) {
                hashMapA.put(nums[i], i);
            }

            //排序B：list
            //数字i在排序B的位置
            HashMap<Integer, Integer> hashMapB = new HashMap<>();
            for (int i = 0; i < length; i++) {
                hashMapB.put(list.get(i), i);
            }

            int ans = 0;
            for (int i = 1; i <= length; i++) {
                int posA = hashMapA.get(i);
                int posB = hashMapB.get(i);
                ans += Math.abs(posA - posB) * values[i - 1];
            }

            res = Math.min(res, ans);
        }

        for (int i = 0; i < nums_sort.length; i++) {
            if (!used[i]) {
                used[i] = true;
                stack.push(nums_sort[i]);

                int size = stack.size();
                if(stack.peek() == nums[size - 1]){
                    stack.pop();
                    used[i] = false;
                    continue;
                }

                subProcess(nums_sort, nums, length, values, stack, used);

                stack.pop();
                used[i] = false;
            }
        }
    }
}
