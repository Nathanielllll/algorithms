package top100.Code_1;

import java.util.HashMap;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * <p>
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class Code_560 {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    result++;
                    continue;
                }
            }
        }
        return result;
    }

    /**
     * 将nums前i个位置的和存储在sum数组当中，因此变成了：
     * 给定一个数组sum，求两数之差为k，一共有多少个这样的
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum_1(int[] nums, int k) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int[] sum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sum.length; i++) {
            int gap = sum[i] - k;
            //gap不在map里面，把sum[i]放入其中
            if (map.containsKey(gap)) {
                result += map.get(gap);
            }

            //map只是在记录value一共出现过几次
            int value = map.get(sum[i]) == null ? 0 : map.get(sum[i]);
            map.put(sum[i], value + 1);
        }
        return result;
    }
}
