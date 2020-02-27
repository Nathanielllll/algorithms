package twoPointers;

//对比一下其和第42题的区别
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
