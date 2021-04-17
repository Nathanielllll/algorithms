package binarySearch;

/**
 * @author luzhi
 * @date 2021/4/15
 */
/*
给定一个非负整数数组 nums 和一个整数m ，你需要将这个数组分成m个非空的连续子数组。

设计一个算法使得这m个子数组各自和的最大值最小。

示例 1：

输入：nums = [7,2,5,10,8], m = 2
输出：18
解释：
一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 */
public class Test_410 {

    public int splitArray(int[] nums, int m) {
        if (nums.length == 1) {
            return nums[0];
        }
        return shipWithinDays(nums, m);
    }

    public int shipWithinDays(int[] weights, int D) {
        int sumWeight = 0;
        for (int weight : weights) {
            sumWeight += weight;
        }

        int left = 1;
        int right = sumWeight;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(weights, D, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean check(int[] weights, int D, int m) {
        int cnt = 0, remainder = m;
        for (int weight : weights) {
            if (weight > m) return false; // 如果包裹重量大于运载力就返回false
            if (weight <= remainder) { // 重量小于还能装载的能力就需要装载
                remainder -= weight;
            } else { // 包裹太重就放到下一次装载
                cnt++;
                remainder = m - weight;
            }
        }
        cnt++; // 需要计算最后一次装载
        return cnt <= D;
    }
}
