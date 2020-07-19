package stack;

import java.util.Arrays;
import java.util.Stack;

/*
给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），
输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

示例 1:

输入: [1,2,1]
输出: [2,-1,2]
解释: 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数；
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。

 */
public class Test_503 {
    public static int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        //1 首先需要解决的问题是，如何实现循环数组，即“最后一个元素的下一个元素是数组的第一个元素”。
        // 事实上，我们只要能遍历两遍数组，效果就等同于循环。
        // 虽然创建一个长度为原数组二倍的数组也可以，但为了额外空间尽可能少，这里采用取模的方式，将索引限制在小于数组长度的正整数范围内。见代码中注释。
        for(int i = 0; i < 2 * nums.length - 1; i++) {
            int index = i % nums.length; // 取模，实现循环数组
            while(!stack.isEmpty() && nums[index] > nums[stack.peek()]) { // 找到下一个更大元素
                result[stack.pop()] = nums[index]; // 栈中保存的是索引
            }
            stack.push(index);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        int[] result = nextGreaterElements(nums);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
