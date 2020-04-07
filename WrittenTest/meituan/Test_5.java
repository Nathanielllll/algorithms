package meituan;

import java.util.Arrays;
import java.util.Stack;

public class Test_5 {
    /**
     * 给你一个长度为n的序列a，请你求出对每一个1<=l<r<=n的区间中最大值和最小值的异或和的异或和。
     *
     * 例如序列为{1,3,5},不同的a(1,2)=1^3,a(1,3)=1^5,a(2,3)=(3^5),a(1,2)^a(1,3)^a(2,3)=0，所以最后的答案是0。
     * @param args
     *
     * 3
     * 1 3 5
     */
    static Stack<Long> stack;
    static long result;
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//
//        long[] nums = new long[n];
//        for (int i = 0; i < n; i++) {
//            nums[i] = scanner.nextLong();
//        }
        long[] nums = {1,3,5};
        Arrays.sort(nums);
        stack = new Stack<>();
        result = 0;
        backTracking(nums, 0);
        System.out.println(result);

    }

    public static void backTracking(long[] nums, int start){
        if (stack.size() == 2) {
            long ans = stack.get(0) ^ stack.get(1);
            result = result ^ ans;
            System.out.println(stack);
        }

        for (int i = start; i < nums.length && stack.size() < 2; i++) {
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }
            stack.push(nums[i]);
            backTracking(nums, i + 1);
            stack.pop();
        }
    }
}
