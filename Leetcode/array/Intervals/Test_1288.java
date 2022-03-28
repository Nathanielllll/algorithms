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
    public static int removeCoveredIntervals(int[][] intervals) {
//        [1,4],[2,8],[3,6]
//        [1,4],[2,8],[2,6]
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });

        int right = intervals[0][1];
        int cnt = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (right >= intervals[i][1]) {
                cnt++;
            }else {
                right = intervals[i][1];
            }
        }
        return intervals.length - cnt;
    }
}
