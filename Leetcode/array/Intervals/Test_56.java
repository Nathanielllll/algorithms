package array.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Test_56 {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        // 必须保证左边界是上升的，不然后续循环体内没法比较
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> res_list = new ArrayList<>();

        int length = intervals.length;
        int left = intervals[0][0];
        int right = intervals[0][1];

        for (int i = 1; i < length; i++) {
            if (right >= intervals[i][0]) {
                left = Math.min(left, intervals[i][0]);
                right = Math.max(right, intervals[i][1]);
            } else {
                res_list.add(new int[]{left, right});
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }
        // 别忘记了
        res_list.add(new int[]{left, right});

        return res_list.toArray(new int[res_list.size()][]);
    }
}
