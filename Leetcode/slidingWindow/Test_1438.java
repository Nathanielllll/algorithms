package slidingWindow;

import java.util.*;

public class Test_1438 {
    /*
    给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。

如果不存在满足条件的子数组，则返回 0 。



示例 1：

输入：nums = [8,2,4,7], limit = 4
输出：2
解释：所有子数组如下：
[8] 最大绝对差 |8-8| = 0 <= 4.
[8,2] 最大绝对差 |8-2| = 6 > 4.
[8,2,4] 最大绝对差 |8-2| = 6 > 4.
[8,2,4,7] 最大绝对差 |8-2| = 6 > 4.
[2] 最大绝对差 |2-2| = 0 <= 4.
[2,4] 最大绝对差 |2-4| = 2 <= 4.
[2,4,7] 最大绝对差 |2-7| = 5 > 4.
[4] 最大绝对差 |4-4| = 0 <= 4.
[4,7] 最大绝对差 |4-7| = 3 <= 4.
[7] 最大绝对差 |7-7| = 0 <= 4.
因此，满足题意的最长子数组的长度为 2 。
示例 2：

输入：nums = [10,1,2,4,7,2], limit = 5
输出：4
解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
示例 3：

输入：nums = [4,2,2,2,4,4,2,2], limit = 0
输出：3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();
        int l = 0, r = 0, res = 0;
        while (r < nums.length) {
            while (!maxQueue.isEmpty() && nums[r] > maxQueue.peekLast())
                maxQueue.removeLast();
            while (!minQueue.isEmpty() && nums[r] < minQueue.peekLast())
                minQueue.removeLast();
            maxQueue.add(nums[r]);
            minQueue.add(nums[r]);
            r++;
            while (maxQueue.peek() - minQueue.peek() > limit) {
                if (maxQueue.peek() == nums[l]) maxQueue.remove();
                if (minQueue.peek() == nums[l]) minQueue.remove();
                l++;
            }
            res = Math.max(res, r - l);
        }
        return res;
    }
}
