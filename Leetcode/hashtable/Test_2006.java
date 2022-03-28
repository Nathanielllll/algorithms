package hashtable;

import java.util.HashMap;

public class Test_2006 {
    /*
    给你一个整数数组nums和一个整数k，请你返回数对(i, j)的数目，满足i < j且|nums[i] - nums[j]| == k。

    |x|的值定义为：

    如果x >= 0，那么值为x。
    如果x < 0，那么值为-x。


    示例 1：

    输入：nums = [1,2,2,1], k = 1
    输出：4
    解释：差的绝对值为 1 的数对为：
    - [1,2,2,1]
    - [1,2,2,1]
    - [1,2,2,1]
    - [1,2,2,1]
    示例 2：

    输入：nums = [1,3], k = 3
    输出：0
    解释：没有任何数对差的绝对值为 3 。
    示例 3：

    输入：nums = [3,2,1,5,4], k = 2
    输出：3
    解释：差的绝对值为 2 的数对为：
    - [3,2,1,5,4]
    - [3,2,1,5,4]
    - [3,2,1,5,4]
     */
    public int countKDifference(int[] nums, int k) {
        HashMap<Integer, Integer> cnt = new HashMap<>();

        int result = 0;
        for (int num : nums) {
            // cnt.getOrDefault()能拿到，当前位置之前的位置，所有的统计数据
            result += cnt.getOrDefault(num - k, 0) + cnt.getOrDefault(num + k, 0);
            // 记录当前位置及之前位置，总共出现的num的数量
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        return result;
    }
}
