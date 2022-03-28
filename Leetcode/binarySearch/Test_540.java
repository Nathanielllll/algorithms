package binarySearch;

public class Test_540 {
    /*
    给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。

请你找出并返回只出现一次的那个数。

你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。



示例 1:

输入: nums = [1,1,2,3,3,4,4,8,8]
输出: 2
示例 2:

输入: nums =  [3,3,7,7,10,11,11]
输出: 10
     */

    public int singleNonDuplicate(int[] nums) {
        int length = nums.length;

        int left = 0;
        int right = length - 1;

        while (left < right) {
            int mid = (left + right) >> 1;

            // 如果mid是奇数，且和nums[mid]相同的数为nums[mid-1]，在右边
            // 如果mid是奇数，且和nums[mid]相同的数为nums[mid+1]，在左边

            // 如果mid是偶数，且和nums[mid]相同的数为nums[mid-1]，在左边
            // 如果mid是偶数，且和nums[mid]相同的数为nums[mid+1]，在右边
            if ((mid & 1) == 1) {
                if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                    left = mid + 1;
                } else if (mid + 1 < length && nums[mid] == nums[mid + 1]) {
                    right = mid - 1;

                } else {
                    return nums[mid];
                }
            } else {
                if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                    right = mid - 1;
                } else if (mid + 1 < length && nums[mid] == nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    return nums[mid];
                }
            }
        }
        return nums[left];
    }
}
