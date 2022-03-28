package array;

import java.util.HashMap;

/**
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于⌊ n/2 ⌋的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * <p>
 * 示例1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class Test_169 {
    //使用投票法。和剑指offer的39题一样
    //https://leetcode-cn.com/problems/majority-element/solution/mo-er-tou-piao-ha-xi-tong-by-javalangruntimeexcept/
    public static int majorityElement(int[] nums) {
        int count = 1;
        int number = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                number = nums[i];
                count = 1;
                continue;
            }

            if (nums[i] == number) {
                count++;
            } else {
                count--;
            }
        }
        return number;
    }


}
