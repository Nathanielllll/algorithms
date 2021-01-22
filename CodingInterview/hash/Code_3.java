package hash;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 数组中重复的数字
 * 在一个长度为n的数组中的所有数字都在0~n-1范围内。数组中某些数字是重复的
 *
 * method_2:
 * 数组中的数字都在0～n-1的范围内，如果这个数组中没有重复的数字，那么当数组排序后的数字i将出现在下标为i的位置。
 *
 * 从头到尾一次扫描这个数组中的每个数字，当扫描到下标为i的数字时，首先比较这个数字(numbers[i])是不是等于i。
 * 如果是，则接着扫描下一个数字。如果不是，则再拿它和第numbers[i](numbers[numbers[i]])个数字进行比较。
 * 如果它和第numbers[i]个数字相等，就找到了一个重复的数字。如果不相等，就把第i个数字和第numbers[i]个数字交换。
 */
public class Code_3 {
    //方法1：使用hashMap来解决问题
    public static ArrayList duplicate_2(int[] numbers) {
        if (numbers == null || numbers.length <= 0) {
            throw new RuntimeException("invalid input");
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0 || numbers[i] >= numbers.length) {
                throw new RuntimeException("invalid input");
            }
        }

        //重复的数字的集合
        ArrayList res = new ArrayList();

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (hashMap.get(numbers[i]) == null) {
                hashMap.put(numbers[i], 1);
            } else {
                res.add(numbers[i]);
            }
        }
        return res;
    }

    // 抽屉原理，查看Leetcode#array#summay.txt有总结
    public static int findRepeatNumber(int[] nums) {
        if(nums==null || nums.length==0){
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            while(nums[i] != nums[nums[i]]){
                swap(nums, i, nums[i]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(i != nums[i]){
                return nums[i];
            }
        }

        return -1;
    }

    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
