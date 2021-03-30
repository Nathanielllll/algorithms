import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class LeetCode_Draft {
    /*
    [0,t],[t+1,2t+1], 因此要num / (t + 1)。比如t==9，则每个桶的大小为10
     */

    private static long getId(long num, long t) {
        return num >= 0 ? num / (t + 1) : (num + 1) / t;
    }

    //在整数数组 nums 中，是否存在两个下标 i 和 j，使得nums [i] 和nums [j]的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t1) {
        if (t1 < 0) {
            return false;
        }

        long t = (long) t1;

        // 桶的个数为k，因此只要在桶当中，必然是满足条件： i 和 j 的差的绝对值也小于等于 ķ
        HashMap<Long, Long> bucket = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long id = getId(nums[i], t);

            if (bucket.containsKey(id)) {
                return true;
            }

            if (bucket.containsKey(id - 1) && Math.abs(bucket.get(id - 1) - nums[i]) <= t) {
                return true;
            }

            if (bucket.containsKey(id + 1) && Math.abs(bucket.get(id + 1) - nums[i]) <= t) {
                return true;
            }


            bucket.put(id, (long) nums[i]);
            if (i >= k) {
                bucket.remove(getId(nums[i - k], t));
            }
        }
        return false;
    }


}