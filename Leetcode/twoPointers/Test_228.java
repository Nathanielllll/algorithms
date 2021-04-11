package twoPointers;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [0,1,2,4,5,7]
 * 输出: ["0->2","4->5","7"]
 * 解释: 0,1,2 可组成一个连续的区间;4,5 可组成一个连续的区间。
 * 示例 2:
 * <p>
 * 输入: [0,2,3,4,6,8,9]
 * 输出: ["0","2->4","6","8->9"]
 * 解释: 2,3,4 可组成一个连续的区间;8,9 可组成一个连续的区间。
 */
public class Test_228 {
    public static List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            while (right < nums.length - 1 && nums[right] + 1 == nums[right + 1]) {
                right++;
            }
            String string;
            if (left != right) {
                string = nums[left] + "->" + nums[right];
            } else {
                string = nums[left] + "";
            }
            result.add(string);

            right++;
            left = right;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0,2,3,4,6,8,9};
        System.out.println(summaryRanges(nums));
    }

}
