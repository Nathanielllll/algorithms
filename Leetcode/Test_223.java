/**
 * @author luzhi
 * @date 2021/3/29
 */
public class Test_223 {
    /*
    0. 首先，我们调整两个矩形，让第一个矩形是靠最左边的；

    1. 先考虑没有重叠的情况，有三种情况，如图所示：
    rectangle1 的下边都大于（等于）rectangle2 的上边，即 B >= H
    rectangle1 的右边都小于（等于）rectangle2的左边，即 C <= E
    rectangle1 的上边都小于（等于）rectangle2的下边，即 D <= F

    2. 要考虑重叠的情况，因为一定有重叠，所以可以找到上下左右边界
    上边界，取两个矩形的上边界的最小值
    下边界，取两个矩形的下边界的最大值
    左边界，取两个矩形的左边界的最大值
    右边界，取两个矩形的右边界的最小值

    得到重叠面积，只需要两个矩形相加减去重叠面积即可！
     */

    /*
    class SolutionEntrance:
    def computeArea(self, A: int, B: int, C: int, D: int, E: int, F: int, G: int, H: int) -> int:
        # 调整两个矩形位置, 让第一个矩形靠最左边
        if A > E:
            return self.computeArea(E, F, G, H, A, B, C, D)
        # 没有重叠的情况
        if B >= H or D <= F or C <= E:
            return abs(A - C) * abs(B - D) + abs(E - G) * abs(F - H)
        # 重叠情况
        # 下边界
        down = max(A, E)
        # 上
        up = min(C, G)
        # 左
        left = max(B, F)
        # 右
        right = min(D, H)
        return abs(A - C) * abs(B - D) + abs(E - G) * abs(F - H) - abs(up - down) * abs(left - right)
     */

    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A > E) {
            return computeArea(E, F, G, H, A, B, C, D);
        }

        if (B >= H || C <= E || D <= F) {
            return computerArea(A, B, C, D) + computerArea(E, F, G, H);
        }

        int left = Math.max(A, E);
        int down = Math.max(B, F);

        int right = Math.min(C, G);
        int up = Math.min(D, H);

        return computerArea(A, B, C, D) + computerArea(E, F, G, H) - computerArea(left, down, right, up);
    }

    private static int computerArea(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) * Math.abs(y1 - y2);
    }

    public static void main(String[] args) {
        System.out.println(computeArea(4, -5,
                5,
                0, -3,
                -3,
                3,
                3));
    }
}
