package slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luzhi
 * @date 2021/3/29
 */
public class Test_220 {
    /*
    在整数数组 nums 中，是否存在两个下标 i 和 j，使得nums [i] 和nums [j]的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。

    如果存在则返回 true，不存在返回 false。

    示例1:

    输入: nums = [1,2,3,1], k = 3, t = 0
    输出: true
     */

    // 方法一：滑动窗口+桶
    private long getID(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) return false;
        // 桶的个数为k，因此只要在桶当中，必然是满足条件： i 和 j 的差的绝对值也小于等于 ķ
        // 桶为[0,t],[t+1,2t+1], 因此要num / (t + 1)。比如t==9，则每个桶的大小为10
        Map<Long, Long> d = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; ++i) {
            // m是当前遍历元素将要在的桶
            long m = getID(nums[i], w);
            // 当前大桶中有这个小桶的话，说明存在重复且符合条件，下面两个条件也一样。每次会对比桶的左右小桶。
            if (d.containsKey(m))
                return true;
            // 如果当前小桶的左边桶存在，且两个桶相差小于t
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d.get(m - 1)) < w)
                return true;
            // 如果当前小桶的右边桶存在，且两个桶相差小于t
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d.get(m + 1)) < w)
                return true;
            // 将这个桶存到大桶里面
            d.put(m, (long) nums[i]);
            // 如果i大于k，也就是大桶装满了，则需要把大桶里面的第一个小桶删了。
            if (i >= k) d.remove(getID(nums[i - k], w));
        }
        return false;
    }



    // 方法二：超时
    public static boolean containsNearbyAlmostDuplicate_1(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - k; j < i; j++) {
                if (j >= 0 && Math.abs((long) nums[i] - (long) nums[j]) <= t) {
                    return true;
                }
            }
        }
        return false;
    }

}
