package search;

/**
 * 在排序数组中查找数字。使用二分查找的思想
 * <p>
 * 1、找到第一个k：
 * 数组中间的数字和k进行比较
 * 1）如果中间的数字比k大，那么k只可能出现在数组的前半段
 * 2）如果中间的26数字比k小，那么k只可能出现在数组的后半段
 * 3）如果中间的数字等于k，1）中间数字的前面一个不是k，那么中间的数字就是第一个k
 * 2）中间数字的前面一个是k，那么第一个k肯定在前半段
 */
public class Code_53 {
    public static int getNumberOfK(int[] data, int k) {
        if (data == null || k < data[0] || k > data[data.length - 1]) {
            throw new RuntimeException("invalid input");
        }
        int first = getFirstK(data, k);
        int last = getLastK(data, k);
        return last - first + 1;
    }

    public static int getFirstK(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                if (mid > 0 && nums[mid] == nums[mid - 1]) {
                    end = mid - 1;
                } else if (mid == 0 || nums[mid] != nums[mid - 1]) {
                    return mid;
                }
            }
        }
        return -1;
    }

    public static int getLastK(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) >> 1;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                if (mid < nums.length - 1 && nums[mid] == nums[mid + 1]) {
                    start = mid + 1;
                } else if (mid == nums.length - 1 || nums[mid] != nums[mid + 1]) {
                    return mid;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {1, 3, 5, 5, 5, 6, 7, 9};
        System.out.println(getFirstK(data, 9));
//        System.out.println(getNumberOfK(data, 5));

    }


}
