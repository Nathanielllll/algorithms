import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
 *
 * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
 *
 * 1. 获取左下角点、右上角的坐标 -> 大矩阵的面积
 * 2. 小矩阵的面积和等于大矩阵的面积
 * 3. 除了四个角落的点会出现一次，其余的点会出现2次或4次
 */
public class Test_391 {
    public boolean isRectangleCover(int[][] rectangles) {
        Map<Pair, Integer> cnt = new HashMap<>();

        long area = 0;

        int lbx = Integer.MAX_VALUE;
        int lby = Integer.MAX_VALUE;
        int rtx = Integer.MIN_VALUE;
        int rty = Integer.MIN_VALUE;

        for(int[] rectangle : rectangles){
            int a = rectangle[0];
            int b = rectangle[1];
            int c = rectangle[2];
            int d = rectangle[3];

            if(lbx > a || lby > b){
                lbx = a;
                lby = b;
            }
            if(c > rtx || d > rty){
                rtx = c;
                rty = d;
            }
            cnt.put(new Pair(a, b), cnt.getOrDefault(new Pair(a, b), 0) + 1);
            cnt.put(new Pair(a, d), cnt.getOrDefault(new Pair(a, b), 0) + 1);
            cnt.put(new Pair(c, b), cnt.getOrDefault(new Pair(a, b), 0) + 1);
            cnt.put(new Pair(c, d), cnt.getOrDefault(new Pair(a, b), 0) + 1);

            area += (long) (c - a) * (d - b);
        }

        long area1 = (long) (rty - lby) * (rtx - lbx);

        if (area != area1) {
            return false;
        }

        for(Map.Entry<Pair, Integer> entry : cnt.entrySet()){
            int x = entry.getKey().x;
            int y = entry.getKey().y;

            if (x == lbx || x == rtx || y == lby || y == rty) {
                if (entry.getValue() != 1) {
                    return false;
                }
            }else {
                if (entry.getValue() != 2 || entry.getValue() != 4) {
                    return false;
                }
            }

        }
        return true;
    }

    class Pair{
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
