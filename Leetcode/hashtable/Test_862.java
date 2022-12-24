package hashtable;

import java.util.Deque;
import java.util.LinkedList;

public class Test_862 {
    /**
     * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
     * <p>
     * 子数组 是数组中 连续 的一部分。
     * <p>
     * dp
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1], k = 1
     * 输出：1
     * 示例 2：
     * <p>
     * 输入：nums = [1,2], k = 4
     * 输出：-1
     * 示例 3：
     * <p>
     * 输入：nums = [2,-1,2], k = 3
     * 输出：3
     * <p>
     * <p>
     * 对于两个索引i1,i2，如果有 i1 < i2 且 preSum[i1] > preSum[i2]，那么必然只能选择i2
     */
    public static int shortestSubarray(int[] nums, int k) {
        // 存储idx
        Deque<Integer> deque = new LinkedList<>();

        int length = nums.length;
        long[] preSums = new long[length + 1];
        for (int i = 1; i <= length; i++) {
            preSums[i] = preSums[i - 1] + nums[i - 1];
            // 特殊case
            if (nums[i - 1] >= k) {
                return 1;
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < preSums.length; i++) {
            while (!deque.isEmpty() && preSums[deque.peekLast()] >= preSums[i]) {
                // 从后面poll出
                deque.pollLast();
            }
            while (!deque.isEmpty() && preSums[i] - preSums[deque.peekFirst()] >= k) {
                // 从前面poll出
                result = Math.min(result, i - deque.pollFirst());
            }
            // 从后面add
            deque.addLast(i);
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
