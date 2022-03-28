package array;

import java.util.Arrays;
import java.util.HashSet;

/*
给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。

示例1:

输入: [1,2,0]
输出: 3
示例2:

输入: [3,4,-1,1]
输出: 2
示例3:

输入: [7,8,9,11,12]
输出: 1

提示：
你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 */
public class Test_41 {
    // 方法三：将数组视为哈希表
    public static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        int len = nums.length;
        // 3,4,-1,1
        for (int i = 0; i < nums.length; i++) {
            while(nums[i] >= 1 && nums[i] <= len && nums[nums[i] - 1] != nums[i]){
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(i != nums[i] - 1){
                return i + 1;
            }
        }

        return len + 1;
    }

    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    // 方法一：哈希表（空间复杂度不符合要求）
    public static int firstMissingPositive_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        HashSet<Integer> hashSet = new HashSet<>();
        for(int num : nums){
            hashSet.add(num);
        }
        for (int i = 1; i <= nums.length; i++) {
            if(!hashSet.contains(i)){
                return i;
            }
        }

        return nums.length + 1;
    }

    // 方法二：排序后找异常（时间复杂度不符合要求）
    public static int firstMissingPositive_2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        Arrays.sort(nums);
        // 3,4,-1,1
        // -1,1,3,4
        int pre = 0;
        for(int num : nums){
            if (num <= 0 || num == pre) {
                continue;
            }else if(num > pre + 1){
                break;
            }
            pre++;
        }
        return pre + 1;
    }
}
