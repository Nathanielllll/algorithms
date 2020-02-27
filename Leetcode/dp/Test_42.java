package dp;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class Test_42 {
    public int trap(int[] height) {
        if (height == null || height.length <= 0) {
            return 0;
        }
        int len = height.length;
        int ans = 0;

        //找到数组中从下标 i 到最左端最高的条形块高度 left_max。
        int[] left_max = new int[len];
        left_max[0] = height[0];
        for (int i = 1; i < len; i++) {
            left_max[i] = Math.max(left_max[i - 1], height[i]);
        }

        //找到数组中从下标 i 到最右端最高的条形块高度 right_max。
        int[] right_max = new int[len];
        right_max[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            right_max[i] = Math.max(right_max[i + 1], height[i]);
        }

        for (int i = 0; i < len; i++) {
            int min = Math.min(left_max[i], right_max[i]);
            ans += (min - height[i]);
        }

        return ans;
    }

}
