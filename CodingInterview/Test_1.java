import java.nio.channels.Pipe;
import java.util.*;

public class Test_1 {

    /*
    给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
    数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
    如果不存在，则输出 -1。

示例 1:

输入: [1,2,1]
输出: [2,-1,2]
解释: 第一个 1 的下一个更大的数是 2；
数字 2 找不到下一个更大的数；
第二个 1 的下一个最大的数需要循环搜索，结果也是 2。

     */
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] nums_1 = new int[len * 2];
        for (int i = 0; i < nums_1.length; i++) {
            nums_1[i] = nums[i % len];
        }

        int[] res = new int[len * 2];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums_1.length; i++) {
            while(!stack.isEmpty() && nums_1[stack.peek()] < nums_1[i]){
                int cur_index = stack.pop();
                res[cur_index] = nums_1[i];
            }
            stack.push(i);
        }

        return Arrays.copyOf(res, len);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        Test_1 test = new Test_1();
        int[] ans = test.nextGreaterElements(nums);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }

}
