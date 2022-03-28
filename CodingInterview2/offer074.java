import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class offer074 {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int length = intervals.length;
        List<int[]> res_list = new ArrayList<>();

        int left = intervals[0][0];
        int right = intervals[0][1];

        for (int i = 1; i < length; i++) {
            int cur_left = intervals[i][0];
            int cur_right = intervals[i][1];
            if(right >= cur_left){
                right = Math.max(right, cur_right);
            }else {
                res_list.add(new int[]{left, right});
                left = cur_left;
                right = cur_right;
            }
        }
        res_list.add(new int[]{left, right});
        return res_list.toArray(new int[res_list.size()][]);
    }
}
