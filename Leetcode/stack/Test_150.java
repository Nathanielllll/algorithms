package stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 根据逆波兰表示法，求表达式的值。
 * 新建一个表达式,如果当前字符为变量或者为数字，则压栈，如果是运算符，则将栈顶两个元素弹出作相应运算，结果再入栈，最后当表达式扫描完后，栈里的就是结果。
 * <p>
 * 有效的运算符包括+,-,*,/。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * <p>
 * 说明：
 * <p>
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例1：
 * <p>
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 */
public class Test_150 {
    public int evalRPN(String[] tokens) {
        Set<String> set = new HashSet<>();
        set.add("+");
        set.add("-");
        set.add("*");
        set.add("/");

        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (set.contains(token)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                if (token.equals("+")) {
                    stack.add(num2 + num1);
                } else if (token.equals("-")) {
                    stack.add(num2 - num1);
                } else if (token.equals("*")) {
                    stack.add(num2 * num1);
                } else if (token.equals("/")) {
                    stack.add(num2 / num1);
                }
            } else {
                stack.add(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
