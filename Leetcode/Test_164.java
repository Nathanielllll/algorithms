import java.util.HashMap;

/**
 * @author luzhi
 * @date 2021/4/19
 */
public class Test_164 {
    /*
    给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。

如果数组元素个数小于 2，则返回 0。

示例1:

输入: [3,6,9,1]
输出: 3
解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
示例2:

输入: [10]
输出: 0
解释: 数组元素个数小于 2，因此返回 0。

     */
    public static int maximumGap(int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        int count = nums.length;
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
            minValue = Math.min(minValue, num);
        }
        int w = (maxValue - minValue + 1) / count + 1;

        int maxId = Integer.MIN_VALUE;
        int minId = Integer.MAX_VALUE;

        // 桶。values[0]表示当前桶的最小值，values[1]表示当前桶的最大值
        HashMap<Integer, Integer[]> bucket = new HashMap<>();
        for (int num : nums) {
            int id = num / w;
            Integer[] values = bucket.getOrDefault(id, new Integer[]{num, num});
            values[0] = Math.min(values[0], num);
            values[1] = Math.max(values[1], num);
            bucket.put(id, values);

            maxId = Math.max(maxId, id);
            minId = Math.min(minId, id);
        }

        int result = bucket.get(minId)[1] - bucket.get(minId)[0];
        int left = minId;
        int right = left + 1;
        while (right <= maxId) {
            while (right <= maxId && !bucket.containsKey(right)) {
                right++;
            }
            result = Math.max(result, bucket.get(right)[0] - bucket.get(left)[1]);
            result = Math.max(result, bucket.get(right)[1] - bucket.get(right)[0]);
            left = right;
            right++;
        }
        return result;
    }
}
