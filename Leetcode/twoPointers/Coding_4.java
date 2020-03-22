package twoPointers;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * L1|R1
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * L2|R2
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 * <p>
 * <p>
 * 假设：
 * cut1 + cut2 = 10。cut1和cut2是在分界线左边的数字数目
 * L1|R1
 * num1 = [3, 4, 5, 8]          cut1 =
 * num2 = [1, 2, 6, 7, 9]
 * L2|R2
 * <p>
 * <p>
 * 当找到中位数的时候，一定是：L1 <= R2, L2 <= R1
 * <p>
 * 否则的话：if L1 > R2: cut1要左移 => (cutL, cut1-1) / 2.0
 * if L2 > R1: cut1要右移 => (cut1+1, cutR) / 2.0
 */
public class Coding_4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null) {
            if (nums2.length % 2 == 1) {
                return nums2[nums2.length / 2];
            } else {
                return (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2.0;
            }
        }
        if (nums2 == null) {
            if (nums1.length % 2 == 1) {
                return nums1[nums1.length / 2];
            } else {
                return (nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2.0;
            }
        }
        //假设nums2的长度要小于nums1的长度
        if (nums1.length >= nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        //假设nums1的长度要小于nums2的长度
        int length = nums1.length + nums2.length;
        int cutL = 0;
        /**特别注意的地方*/
        int cutR = nums1.length;

        while (true) {
            int cut1 = (cutL + cutR) / 2;
            int cut2 = length / 2 - cut1;

            int L1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int R1 = cut1 == nums1.length ? Integer.MAX_VALUE : nums1[cut1];
            int L2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];
            int R2 = cut2 == nums2.length ? Integer.MAX_VALUE : nums2[cut2];

            if (L1 > R2) {
                cutR = cut1 - 1;
            } else if (L2 > R1) {
                cutL = cut1 + 1;
            // 当找到中位数的时候，一定是：L1 <= R2, L2 <= R1
            } else {
                if (length % 2 == 0) {
                    return (Math.min(R1, R2) + Math.max(L1, L2)) / 2.0;
                } else {
                    return (Math.min(R1, R2));
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums2 = {1};
        int[] nums1 = {2, 3, 4, 5};
//        int[] nums1 = {3, 4, 5, 8};
//        int[] nums2 = {1, 2, 6, 7, 9,10};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
