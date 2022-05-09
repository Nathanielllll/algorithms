package array;


import java.util.Arrays;

public class Test_253 {
    /*
    给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，返回 所需会议室的最小数量 。



示例 1：

输入：intervals = [[0,30],[5,10],[15,20]]
输出：2
示例 2：

输入：intervals = [[7,10],[2,4]]
输出：1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/meeting-rooms-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minMeetingRooms(int[][] intervals) {
        /*
        开会也可以理解成坐公交，都是占用某个资源。
就拿题目给的第一组数组来分析。


intervals = [[0,30],[5,10],[15,20]]
第一个人从0上车，从30下车；
第二个人从5上车，10下车。。。

我们的问题转化为最多车上有几个人（也就是最多有多少会议室）。

显然：上车，车上人数+1；下车，车上人数-1
我们把intervals拆解一下


上车：[0, 1], [5, 1], [15, 1]

下车：[10, -1], [20, -1], [30, -1]
然后按照第一个数把上下车排好序


人数 1    2     1     2     1      0
     0----5----10----15----20-----30
变化 +1   +1    -1    +1    -1    -1
最多车上两个人。
         */
        int length = intervals.length;
        int[][] data = new int[length * 2][2];
        int i = 0;
        for (int[] interval : intervals) {
            data[i++] = new int[]{interval[0], 1};
            data[i++] = new int[]{interval[1], -1};
        }

        //按时间升序
        Arrays.sort(data, ((o1, o2) -> {
            int x = o1[0] - o2[0];
            //当开始和结束时间相同，要保证先下车
            if (x == 0) {
                return o1[1] - o2[1];
            } else {
                return x;
            }
        }));

        int result = 0;
        int cur = 0;
        for (int j = 0; j < 2 * length; j++) {
            cur += data[j][1];
            result = Math.max(result, cur);
        }
        return result;
    }
}
