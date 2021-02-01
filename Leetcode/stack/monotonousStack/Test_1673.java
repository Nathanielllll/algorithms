package stack.monotonousStack;

import java.util.Stack;

/**
 * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
 *
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
 *
 * 在子序列 a 和子序列 b 第一个不相同的位置上，如果 a 中的数字小于 b 中对应的数字，那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,5,2,6], k = 2
 * 输出：[2,6]
 * 解释：在所有可能的子序列集合 {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]} 中，[2,6] 最具竞争力。
 * 示例 2：
 *
 * 输入：nums = [2,4,3,3,5,4,9,6], k = 4
 * 输出：[2,3,3,4]
 */
public class Test_1673 {
    /*
    思路：
    1.当前元素大于等于栈顶元素，入栈；否则，将栈中大于当前元素的数一直出栈，再将当前元素压栈，以此保证栈中最小字典序的顺序。
    2.其中记录删除了多少元素，题目保留k个元素，即删除n-k个元素，若删除的数目达到上限，则保留栈中元素不再删除。
    3.遍历一遍后，若栈中元素数目大于k个（即n-k>0），将多余元素出栈。
     */
    public static int[] mostCompetitive(int[] nums, int k) {
        int count = nums.length - k;
        Stack<Integer> stack = new Stack<>();
        for(int num : nums){
            while (!stack.isEmpty() && stack.peek() > num && count > 0) {
                stack.pop();
                count--;
            }
            stack.push(num);
        }
        int[] result = new int[k];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.get(i);
        }
        return result;
    }
}
