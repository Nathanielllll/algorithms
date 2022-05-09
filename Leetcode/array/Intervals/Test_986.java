package array.Intervals;

import java.util.ArrayList;
import java.util.List;

public class Test_986 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < A.length && j < B.length) {
            int left = Math.max(A[i][0], B[j][0]);// 交集区间的左端，取它们的较大者
            int right = Math.min(A[i][1], B[j][1]);// 交集区间的右端，取它们的较小者
            if (left <= right) {// 形成了交集区间
                res.add(new int[] {left, right});
            }
            //求完一个交集区间后，较早结束的子区间，不可能再和别的子区间重叠，它的指针要移动。
            //较长的子区间还有可能和别人重叠，它的指针暂时不动。
            if (A[i][1] < B[j][1]) {// 谁先结束，谁的指针就步进，考察下一个子区间
                i++;
            } else {
                j++;
            }
        }
        return res.toArray(new int[0][0]);
    }
}
