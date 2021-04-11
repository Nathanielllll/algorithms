package twoPointers;

import java.util.Arrays;

public class Test_581 {

    /*
    给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

    请你找出符合题意的 最短 子数组，并输出它的长度。


    示例 1：

    输入：nums = [2,6,4,8,10,9,15]
    输出：5
    解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
    示例 2：

    输入：nums = [1,2,3,4]
    输出：0
     */

    // O(Nlog(N))
    public int findUnsortedSubarray(int[] nums) {
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);

        int p1 = 0;
        while (p1 < nums.length) {
            if (nums[p1] != sortedNums[p1]) {
                break;
            }
            p1++;
        }
        if (p1 == nums.length) {
            return 0;
        }

        int p2 = nums.length - 1;
        while (p2 >= 0) {
            if (nums[p2] != sortedNums[p2]) {
                break;
            }
            p2--;
        }
        return p2 - p1 + 1;
    }

    // O(N)

}
