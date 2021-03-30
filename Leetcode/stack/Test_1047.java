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
            if (!stack.isEmpty() && S.charAt(i) == stack.peek()) {
                stack.pop();
                continue;
            }
            stack.push(S.charAt(i));
        }
        StringBuffer res = new StringBuffer();
        for(Character c : stack){
            res.append(c);
        }
        return res.toString();
    }
}
