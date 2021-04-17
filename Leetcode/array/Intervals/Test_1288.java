package array.Intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author luzhi
 * @date 2021/3/28
 */
public class Test_1288 {
    /*
    给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。

    只有当c <= a且b <= d时，我们才认为区间[a,b) 被区间[c,d) 覆盖。

    在完成所有删除操作后，请你返回列表中剩余区间的数目。

    示例：

    输入：intervals = [[1,4],[3,6],[2,8]]
    输出：2
    解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
     */
    public int removeCoveredIntervals(int[][] intervals) {
        // 先按起始区间排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int cnt = intervals.length;
        // 记录前一个区间
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (left <= intervals[i][0] && intervals[i][1] <= right) {
                // 前一个区间覆盖了后一个
                cnt--;
            } else if (left == intervals[i][0] && right <= intervals[i][1]) {
                // 后一个区间覆盖了前一个
                cnt--;
                right = Math.max(right, intervals[i][1]);
            } else {
                // 谁也没有覆盖谁，继续往下走
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }

        return cnt;
    }
}
