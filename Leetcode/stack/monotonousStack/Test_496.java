package stack.monotonousStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/*
给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。

nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

 

示例 1:

输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
    对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
    对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
示例 2:

输入: nums1 = [2,4], nums2 = [1,2,3,4].
输出: [3,-1]
解释:
    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。

 */
public class Test_496 {
    public static void main(String[] args) {
        int[] nums1 = {2,4};
        int[] nums2 = {1,2,3,4};
        int[] ans = nextGreaterElement(nums1, nums2);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }


    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums2.length];
        Arrays.fill(res, -1);

        Stack<Integer> stack = new Stack<>();
        //右边离i位置最近的，且比arr[i]小，使用单调递增栈
        for (int i = 0; i < nums2.length; i++) {
            while(!stack.isEmpty() && nums2[stack.peek()] < nums2[i]){
                int index = stack.pop();
                res[index] = nums2[i];
            }
            stack.push(i);
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < res.length; i++) {
            hashMap.put(nums2[i], res[i]);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = hashMap.get(nums1[i]);
        }
        return ans;
    }
}
