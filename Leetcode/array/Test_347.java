package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个非空的整数数组，返回其中出现频率前k高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2 输出: [1,2] 示例 2:
 * <p>
 * 输入: nums = [1], k = 1 输出: [1] 说明：
 * <p>
 * 你可以假设给定的k总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 你的算法的时间复杂度必须优于 O(n log n) ,n是数组的大小。
 */
public class Test_347 {

  public int[] topKFrequent(int[] nums, int k) {
    if (nums == null) {
      return new int[]{};
    }

    Map<Integer, Integer> cnt = new HashMap<>();
    for (int num : nums) {
      cnt.put(num, cnt.getOrDefault(num, 0) + 1);
    }

    List<Integer>[] freq = new List[nums.length + 1];
    for (int i = 0; i < freq.length; i++) {
      freq[i] = new ArrayList<>();
    }
    for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
      freq[entry.getValue()].add(entry.getKey());
    }
    int[] result = new int[k];
    int idx = 0;
    outer:
    for (int i = nums.length; i >= 0; i--) {
      List<Integer> list = freq[i];
      for (Integer integer : list) {
        result[idx] = integer;
        idx++;
        if (idx == k) {
          break outer;
        }
      }
    }
    return result;
  }

}
