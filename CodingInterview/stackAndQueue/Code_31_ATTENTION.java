package stackAndQueue;

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 * <p>
 * 压入栈的所有数字均不相等
 * <p>
 * 要记住规律才能写
 */
public class Code_31_ATTENTION {
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for(int push_num : pushed){
            stack.push(push_num);
            while (!stack.isEmpty() && stack.peek() == popped[index]){
                stack.pop();
                index++;
            }
        }
        return index == pushed.length;
    }
}
