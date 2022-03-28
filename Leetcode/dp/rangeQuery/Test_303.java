package dp.rangeQuery;

/**
 * 给定一个整数数组 nums，求出数组从索引i到j(i≤j) 范围内元素的总和，包含i, j两点。
 *
 * 示例：
 *
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *         [0, -2,-2, 1, -4, -2, -3]
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 *
 * 你可以假设数组不可变。
 * 会多次调用sumRange方法。
 *
 * 求区间 i ~ j 的和，可以转换为 sum[j + 1] - sum[i]，其中 sum[i] 为 0 ~ i - 1 的和。
 */
public class Test_303 {

    public class NumArray{
        private int[] sums;

        public NumArray(int[] nums) {
            int s = 0;
            sums = new int[nums.length + 1];
            for (int i = 0; i < nums.length; ++i) {
                s += nums[i];
                sums[i + 1] = s;
            }
        }

        public int sumRange(int i, int j) {
            return sums[j + 1] - sums[i];
        }
    }


}
