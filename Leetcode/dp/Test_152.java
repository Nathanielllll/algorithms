package dp;

/**
 * 给定一个整数数组 nums，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4] 输出: 6 解释:子数组 [2,3] 有最大乘积 6。 示例 2:
 * <p>
 * 输入: [-2,0,-1] 输出: 0 解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * <p>
 * 当 nums[i] >= 0 并且dpMax[i-1] > 0，dpMax[i] = dpMax[i-1] * nums[i] 当 nums[i] >= 0 并且dpMax[i-1] <
 * 0，此时如果和前边的数累乘的话，会变成负数，所以dpMax[i] = nums[i] 当 nums[i] <
 * 0，此时如果前边累乘结果是一个很大的负数，和当前负数累乘的话就会变成一个更大的数。所以我们还需要一个数组 dpMin 来记录以第 i 个元素的结尾的子数组，乘积最小的值。 当dpMin[i-1]
 * < 0，dpMax[i] = dpMin[i-1] * nums[i] 当dpMin[i-1] >= 0，dpMax[i] = nums[i] 因此：dpMax[i] =
 * max(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i]);
 * <p>
 * <p>
 * 求乘积最大的连续子序列: dp_max dp_min 以第 i 个元素的结尾的子数组,乘积最小的值
 */
public class Test_152 {

  public int maxProduct(int[] nums) {
    int dp_min = nums[0];
    int dp_max = nums[0];
    int result = nums[0];

    for (int i = 1; i < nums.length; i++) {
      int num = nums[i];
      int pre_max = dp_max;
      int pre_min = dp_min;
      dp_max = Math.max(num, Math.max(pre_max * num, pre_min * num));
      dp_min = Math.min(num, Math.min(pre_max * num, pre_min * num));

      result = Math.max(dp_max, result);
    }
    return result;
  }
}
