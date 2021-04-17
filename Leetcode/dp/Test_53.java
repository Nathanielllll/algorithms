package dp;

/**
 * 给定一个整数数组 nums，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释:连续子数组[4,-1,2,1] 的和最大，为6。
 */
public class Test_53 {
    public int maxSubArray_1(int[] nums) {
        int len = nums.length;
        int sum = nums[0];
        int max = nums[0];

        for (int i = 1; i < len; i++) {
            if (sum < 0) {
                sum = nums[i];
            }else {
                sum += nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    /*
    sum(i,j) = sum(0,j) - sum(0,i)

    我们只要记录前i总和最小值就可以了!
     */
    public int maxSubArray(int[] nums) {
        int all_sum = 0;
        int min_sum = 0;
        int result = Integer.MIN_VALUE;
        for(int num : nums){
            all_sum += num;
            result = Math.max(result, all_sum - min_sum);
            min_sum = Math.min(min_sum, all_sum);
        }
        return result;
    }
}
