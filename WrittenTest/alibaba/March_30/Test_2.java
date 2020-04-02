package alibaba.March_30;

import java.util.Arrays;
import java.util.Stack;

public class Test_2 {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Arrays.sort(nums);
        total(nums, 0);
        double result = ((double)sum) / ((double)count);
        System.out.println(result);
    }

    //先排序
    static Stack<Integer> stack = new Stack<>();
    static int count = 0;
    static long sum = 0;
    public static void total(int[] nums, int start){
        if (stack.size() < nums.length && !stack.isEmpty()) {
            count++;
            sum += stack.peek();
        }

        //假设每个数字不重复
        for (int i = start; i < nums.length; i++) {
            if(i>start && nums[i] == nums[i-1]){
                continue;
            }
            stack.push(nums[i]);
            total(nums, i + 1);
            stack.pop();
        }
    }
}
