package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个大小为n的数组，找出其中所有出现超过⌊ n/3 ⌋次的元素。
 * <p>
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 * <p>
 * 示例1:
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
    public static List<Integer> majorityElement_1(int[] nums) {
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

    /*
    解法2：moore投票法，O(N)时间，O(1)空间。本质上是利用两个变量cm, cn记录频率最高的两个元素m, n的频率，遇到m,n自增对应的频率，遇到非m,非n，自减cm,cn。
    最后再重置cm,cn为0，再遍历一遍数组查看获取的最高频率的m，n的频率是否大于1/3的总元素个数。
    因为有可能最高频率的元素并不大于1/3的总元素个数(比如[1,1,2,2,3,4,5,6,7,8,9])
     */
    public static List<Integer> majorityElement(int[] nums) {
        // 最多的数
        int max_first = 0;
        // 最多的第二个数
        int max_second = 0;
        // 最多的数的频率
        int count_first = 0;
        // 最多的第二个数的频率
        int count_second = 0;

        for (int num : nums) {
            if (num == max_first) {
                count_first++;
            } else if (num == max_second) {
                count_second++;
            } else if (count_first == 0) {
                count_first = 1;
                max_first = num;
            } else if (count_second == 0) {
                count_second = 1;
                max_second = num;
            } else {
                count_first--;
                count_second--;
            }
        }

        count_first = 0;
        count_second = 0;
        for (int num : nums) {
            if (max_first == num) {
                count_first++;
            } else if (max_second == num) {
                count_second++;
            }
        }

        List<Integer> result = new ArrayList<>();
        int targetCount = nums.length / 3 + 1;
        if (count_first >= targetCount) {
            result.add(max_first);
        }
        if (count_second >= targetCount) {
            result.add(max_second);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 3, 2, 2, 2};
        System.out.println(majorityElement(nums));
    }
}
