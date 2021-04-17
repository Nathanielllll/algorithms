package array;

import java.util.HashSet;

/**
 * @author luzhi
 * @date 2021/4/16
 */
public class Test_128 {
    /*
    给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

    进阶：你可以设计并实现时间复杂度为O(n) 的解决方案吗？

    示例 1：

    输入：nums = [100,4,200,1,3,2]
    输出：4
    解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
    示例 2：

    输入：nums = [0,3,7,2,5,8,4,6,0,1]
    输出：9
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            hashSet.add(num);
        }
        int result = 0;

        for (int num : nums) {
            // 靠这个条件，时间复杂度从N^2降到了N
            if (!hashSet.contains(num - 1)) {
                int curLength = 0;
                while (hashSet.contains(num++)) {
                    curLength++;
                }
                result = Math.max(result, curLength);
            }
        }
        return result;
    }
}
