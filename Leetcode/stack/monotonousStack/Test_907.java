package stack.monotonousStack;

import java.util.Deque;
import java.util.LinkedList;

public class Test_907 {
    /*
    给定一个整数数组 arr，找到 min(b)的总和，其中 b 的范围为 arr 的每个（连续）子数组。

由于答案可能很大，因此 返回答案模 10^9 + 7 。

示例 1：

输入：arr = [3,1,2,4]
输出：17
解释：
子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。 
最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
示例 2：

输入：arr = [11,81,94,43,3]
输出：444

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sum-of-subarray-minimums
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


https://leetcode-cn.com/problems/sum-of-subarray-minimums/solution/dan-diao-zhan-zuo-you-liang-bian-di-yi-g-ww3n/

这道题的本质在于找到数组中的每一个数作为最小值的范围，比如对于某个数nums[i]能够最小值以这种形式表示：左边连续m个数比nums[i]大，右边连续n个数比nums[i]大。

     */
    private Integer MOD = 1_000_000_007;

    public int sumSubarrayMins(int[] arr) {
        // 使用单调递增栈，找到左右两边离它最近的，比他小的位置。这样，就可以找到左右两边，离它最远的，比它大的位置。
        Deque<Integer> stack = new LinkedList<>();
        int res = 0;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            // 这道题目中，是 > 还是 >= 无关紧要。
            // 如果是 >= 则说明单调栈是严格单调的；否则说明是非严格单调栈。
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                int curIdx = stack.poll();
                int leftIdx = stack.isEmpty() ? -1 : stack.peek();
                int rightIdx = i;
                //0,4,7
                // 1  ,3,4,5, 2 ,5,3,  1
                int leftCount = curIdx - leftIdx - 1;
                int rightCount = rightIdx - curIdx - 1;
                // 为什么还要 * arr[curIdx]？
                // 因为(leftCount + 1) * (rightCount + 1) 是表示有那么多数量，而题目是要求 那么多数量的arr[curIdx]的和
                res += ((leftCount + 1) * (rightCount + 1) * arr[curIdx]) % MOD;
                res %= MOD;
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            int curIdx = stack.poll();
            int leftIdx = stack.isEmpty() ? -1 : stack.peek();
            int rightIdx = length;
            //0,4,7
            // 1  ,3,4,5, 2 ,5,3,  1
            int leftCount = curIdx - leftIdx - 1;
            int rightCount = rightIdx - curIdx - 1;
            res += ((leftCount + 1) * (rightCount + 1) * (long)arr[curIdx]) % MOD;
            res %= MOD;
        }

        return res;
    }
}
