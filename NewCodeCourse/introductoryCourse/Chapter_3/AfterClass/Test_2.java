package introductoryCourse.Chapter_3.AfterClass;

import java.util.ArrayList;

/*
给定一组不重叠的时间区间，在时间区间中插入一个新的时间区间(如果有重叠的话就合并区间)。
这些时间区间初始是根据它们的开始时间排序的。
示例1:
给定时间区间[1,3]，[6,9]，在这两个时间区间中插入时间区间[2,5]，并将它与原有的时间区间合并，变成[1,5],[6,9].
示例2：
给定时间区间[1,2],[3,5],[6,7],[8,10],[12,16],在这些时间区间中插入时间区间[4,9]，并将它与原有的时间区间合并，变成[1,2],[3,10],[12,16].
这是因为时间区间[4,9]覆盖了时间区间[3,5],[6,7],[8,10].

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals[1,3],[6,9], insert and merge[2,5]in as[1,5],[6,9].

Example 2:
Given[1,2],[3,5],[6,7],[8,10],[12,16], insert and merge[4,9]in as[1,2],[3,10],[12,16].

This is because the new interval[4,9]overlaps with[3,5],[6,7],[8,10].

具体思路是，用一个变量 cur 来遍历区间，如果当前 cur 区间的结束位置小于要插入的区间的起始位置的话，说明没有重叠，
则将 cur 区间加入结果 res 中，然后 cur 自增1。直到有 cur 越界或有重叠。处理所有重叠的区间，
每次用取两个区间起始位置的较小值，和结束位置的较大值来更新。直到 cur 越界或者没有重叠时 while 循环退出。
之后将更新好的新区间加入结果 res，然后将 cur 之后的区间再加入结果 res 中即可。
 */
public class Test_2 {
    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<>();
        if (intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }

        int i = 0;
        for (; i < intervals.size(); i++) {
            if (newInterval.end < intervals.get(i).start) {
                break;
            } else if (newInterval.start > intervals.get(i).end) {
                result.add(intervals.get(i));
            } else {
                newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
                newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            }
        }
        result.add(newInterval);
        for (; i < intervals.size(); i++) {
            result.add(intervals.get(i));
        }
        return result;
    }
}
