package array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luzhi
 * @date 2021/3/28
 */
public class Test_57 {
    /*
    给你一个 无重叠的 ，按照区间起始端点排序的区间列表。

    在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
    
    示例1：
    
    输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
    输出：[[1,5],[6,9]]
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result_list = new ArrayList<>();
        int idx = 0;
        int length = intervals.length;

        // 首先将新区间左边且相离的区间加入结果集
        while (idx < length && intervals[idx][1] < newInterval[0]) {
            result_list.add(intervals[idx++]);
        }

        // 接着判断当前区间是否与新区间重叠，重叠的话就进行合并，直到遍历到当前区间在新区间的右边且相离，
        // 将最终合并后的新区间加入结果集
        while (idx < length && intervals[idx][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[idx][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[idx][1], newInterval[1]);
            idx++;
        }
        result_list.add(newInterval);

        // 最后将新区间右边且相离的区间加入结果集
        while (idx < length) {
            result_list.add(intervals[idx++]);
        }

        return result_list.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] newInterval = {4,8};
        //  [[1,2],[3,10],[12,16]]
        System.out.println(insert(intervals, newInterval));
    }


}
