package array.Intervals;

import java.util.Arrays;

/**
 * @author luzhi
 * @date 2021/4/16
 */
public class Test_435_ATTENTION {
    /*
    给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

    注意:

    可以认为区间的终点总是大于它的起点。
    区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
    示例 1:

    输入: [ [1,2], [2,3], [3,4], [1,3] ]

    输出: 1

    解释: 移除 [1,3] 后，剩下的区间没有重叠。
    示例 2:

    输入: [ [1,2], [1,2], [1,2] ]

    输出: 2

    解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
    示例 3:

    输入: [ [1,2], [2,3] ]

    输出: 0
     */

    public static void main(String[] args) {
        int[][] intervals = {{2, 3}, {1, 2}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    //1，如果后面区间的头小于当前区间的尾，
    //比如当前区间是[3,6]，后面区间是[4,5]或者是[5,9]
    //说明这两个区间有重复，必须要移除一个，那么要移除哪个呢，为了防止在下一个区间和现有区间有重叠，我们应该让现有区间越短越好，所以应该移除尾部比较大的，保留尾部比较小的。
    //2，如果后面区间的头不小于当前区间的尾，说明他们没有重合，不需要移除
    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        int res = 0;
        int length = intervals.length;
        int right = intervals[0][1];
        for (int i = 1; i < length; i++) {
            if (intervals[i][0] < right) {
                right = Math.min(right, intervals[i][1]);
                res++;
            } else {
                right = intervals[i][1];
            }
        }
        return res;
    }

    // dp, 时间复杂度为O(N^2)
    public static int eraseOverlapIntervals_dp(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });

        int maxLength = 1;
        int[] dp = new int[intervals.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < i; j++) {
                if (intervals[i][0] >= intervals[j][1]) {
                    dp[i] = dp[j] + 1;
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return intervals.length - maxLength;
    }
}
