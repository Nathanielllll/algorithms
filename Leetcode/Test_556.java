public class Test_556 {
    /*
    给你一个正整数n ，请你找出符合条件的最小整数，其由重新排列 n中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。

注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。

示例 1：

输入：n = 12
输出：21
示例 2：

输入：n = 21
输出：-1
     */

    public static int nextGreaterElement(int n) {
        if (n <= 9) {
            return -1;
        }
        char[] chars = String.valueOf(n).toCharArray();

        // 1. 判断已经是当前位数的最大值
        int idx = 0;
        while (idx < chars.length - 1 && chars[idx] >= chars[idx + 1]) {
            ++idx;
        }
        if (idx == chars.length - 1) {
            return -1;
        }

        // 2. nextPermutation
        nextPermutation(chars);
        long long_res = Long.parseLong(String.valueOf(chars));
        if (long_res > Integer.MAX_VALUE || long_res == n) {
            return -1;
        }
        return (int) long_res;
    }

    public static void nextPermutation(char[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }
        int j = i + 1;

        if (j > 0) {
            int k = nums.length - 1;
            while (k >= j && nums[k] <= nums[i]) {
                --k;
            }

            swap(nums, i, k);
        }
        reverse(nums, j, nums.length - 1);
    }

    private static void reverse(char[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private static void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
