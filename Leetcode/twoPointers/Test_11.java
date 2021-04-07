package twoPointers;

//对比一下其和第42题的区别

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 */
public class Test_11 {
    public static int maxArea(int[] heights) {
        if (heights == null || heights.length <= 0) {
            return 0;
        }
        int maxArea = 0;
        int left = 0;
        int right = heights.length - 1;
        while (left < right) {
            int min = Math.min(heights[left], heights[right]);
            maxArea = Math.max(maxArea, (right - left) * min);
            if (heights[left] < heights[right])
                left++;
            else
                right--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(heights));
    }
}
