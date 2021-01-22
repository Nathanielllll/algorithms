package top100.Code_84;

import java.util.Stack;

/**
 * method_2
 * 在这种方法中，我们维护一个栈。一开始，我们把 -1 放进栈的顶部来表示开始。
 * 初始化时，按照从左到右的顺序，我们不断将柱子的序号放进栈中，直到遇到相邻柱子呈下降关系，也就是 a[i-1] > a[i]a[i−1]>a[i] 。
 * 现在，我们开始将栈中的序号弹出，直到遇到 stack[j]stack[j] 满足a\big[stack[j]\big] \leq a[i]a[stack[j]]≤a[i] 。
 * 每次我们弹出下标时，我们用弹出元素作为高形成的最大面积矩形的宽是当前元素与 stack[top-1]stack[top−1] 之间的那些柱子。
 * 也就是当我们弹出 stack[top]stack[top] 时，记当前元素在原数组中的下标为 i ，当前弹出元素为高的最大矩形面积为：
 * <p>
 * (i-stack[top-1]-1) \times a\big[stack[top]\big].
 * (i−stack[top−1]−1)×a[stack[top]].
 * <p>
 * 更进一步，当我们到达数组的尾部时，我们将栈中剩余元素全部弹出栈。在弹出每一个元素是，我们用下面的式子来求面积：
 * (stack[top]-stack[top-1]) \times a\big[stack[top]\big](stack[top]−stack[top−1])×a[stack[top]]，
 * 其中，stack[top]stack[top]表示刚刚被弹出的元素。因此，我们可以通过每次比较新计算的矩形面积来获得最大的矩形面积。
 */
public class Coding_84 {
    public static int largestRectangleArea(int[] heights) {
        int result = 0;
        // 找两边比它小的数字
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int curHeight = heights[stack.pop()];
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                int rightIndex = i;
                result = Math.max(result, curHeight * (rightIndex - leftIndex - 1));
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int curHeight = heights[stack.pop()];
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            int rightIndex = heights.length;
            result = Math.max(result, curHeight * (rightIndex - leftIndex - 1));
        }
        return result;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(heights));
    }
}
