package binarySearch;

/**
 * @author luzhi
 * @date 2021/3/7
 */
public class Test_287 {
    // 方法一：
    public int findDuplicate_1(int[] nums) {
        int len = nums.length;
        int left = 1;
        int right = len - 1;
        while (left < right) {
            // 在 Java 里可以这么用，当 left + right 溢出的时候，无符号右移保证结果依然正确
            int mid = (left + right) >>> 1;

            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt += 1;
                }
            }

            // 根据抽屉原理，小于等于 4 的个数如果严格大于 4 个
            // 此时重复元素一定出现在 [1, 4] 区间里
            if (cnt > mid) {
                // 重复元素位于区间 [left, mid]
                right = mid;
            } else {
                // if 分析正确了以后，else 搜索的区间就是 if 的反面
                // [mid + 1, right]
                left = mid + 1;
            }
        }
        return left;
    }


    // 方法二：
    public static int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while(i != nums[i] - 1){
                if(nums[i] != nums[nums[i] - 1]){
                    swap(nums, i, nums[i] - 1);
                }else {
                    return nums[i];
                }
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
