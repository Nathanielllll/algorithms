package array.Intervals;

import java.util.Arrays;
import java.util.List;

/**
 * @author luzhi
 * @date 2021/3/28
 */
public class Test_252 {

  /*
  给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，请你判断一个人是否能够参加这里面的全部会议。

  示例 1:：
  输入: intervals = [[0,30],[5,10],[15,20]]
  输出: false
  解释: 存在重叠区间，一个人在同一时刻只能参加一个会议。

  示例 2:：
  输入: intervals = [[7,10],[2,4]]
  输出: true
  解释: 不存在重叠区间。
   */
  public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, ((o1, o2) -> o1[0] - o2[0]));

    for (int i = 0; i < intervals.length - 1; i++) {
      // 遍历会议，如果下一个会议在前一个会议结束之前就开始了，返回 false
      if (intervals[i][1] > intervals[i + 1][0]) {
        return false;
      }
    }

    return true;
  }

  public class Interval {

    public int start, end;

    public Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public boolean canAttendMeetings2(List<Interval> intervals) {
    intervals.sort(((o1, o2) -> o1.start - o2.start));
    int n = intervals.size();
    for (int i = 0; i < n - 1; i++) {
      // 遍历会议，如果下一个会议在前一个会议结束之前就开始了，返回 false
      if (intervals.get(i).end > intervals.get(i + 1).start) {
        return false;
      }
    }
    return true;
  }
}
