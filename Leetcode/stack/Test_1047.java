package stack;

import java.util.Stack;

/**
 * @author luzhi
 * @date 2021/3/9
 */
public class Test_1047 {
    public static String removeDuplicates(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (stack.isEmpty() || stack.peek() != ch) {
                stack.push(ch);
            } else {
                stack.pop();
            }
        }
        StringBuffer resultBuffer = new StringBuffer();
        for (char ch : stack) {
            resultBuffer.append(ch);
        }
        return resultBuffer.toString();
    }
}
