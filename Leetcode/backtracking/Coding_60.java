package backtracking;

import java.util.Stack;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 说明：
 *
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 *
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 *
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class Coding_60 {

    private String result = "";
    private int count = 0;
    private boolean[] used;

    public String getPermutation(int n, int k) {
        if (n < 1) {
            return result;
        }

        // 初始化参数
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        used = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        getPermutation(nums, k, stack);
        return result;
    }

    private void getPermutation(int[] nums, int k, Stack<Integer> stack) {
        if (count > k) {
            return;
        }
        if (stack.size() == nums.length) {
            count++;
            if (count == k) {
                StringBuffer stringBuffer = new StringBuffer();
                for(int num:stack){
                    stringBuffer.append(num);
                }
                result = stringBuffer.toString();
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                stack.push(nums[i]);

                getPermutation(nums, k,  stack);

                stack.pop();
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Coding_60 solution = new Coding_60();
        System.out.println(solution.getPermutation(3, 3));
    }
}
