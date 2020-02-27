package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <p>
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: [3]
 * 示例 2:
 * <p>
 * 输入: [1,1,1,3,3,2,2,2]
 * 输出: [1,2]
 * <p>
 */
public class Test_229 {
    public static List<Integer> majorityElement(int[] nums) {
        int threshold = nums.length / 3;
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int count = hashMap.getOrDefault(nums[i], 0) + 1;
            hashMap.put(nums[i], count);
            //只在这个时候加入
            if (count == threshold + 1) {
                result.add(nums[i]);
            } else if (count > threshold + 1) {
                continue;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,3,3,2,2,2};
        System.out.println(majorityElement(nums));
    }
}
