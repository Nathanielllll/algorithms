package stack;

import java.util.Stack;

/**
 * 给定一个只包含三种字符的字符串：（，）和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 *
 * 任何左括号 (必须有相应的右括号 )。
 * 任何右括号 )必须有相应的左括号 (。
 * 左括号 ( 必须在对应的右括号之前 )。
 * *可以被视为单个右括号 )，或单个左括号 (，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * 示例 1:
 *
 * 输入: "()"
 * 输出: True
 * 示例 2:
 *
 * 输入: "(*)"
 * 输出: True
 * 示例 3:
 *
 * 输入: "(*))"
 * 输出: True
 *
 *
 * “（”必须在“”的左边
 * “）”必须在“”的右边
 *
 * 使用两个栈，left存左括号，star存“*”，存储的内容是字符在字符串中的下标。
 *
 * 遍历字符串的每一个字符，如果是“（”存储下标到left中；
 * 如果是“*”存储下标到star中；
 * 如果是“）”，先从left中获取“（”，并将栈顶元素出栈，如果left为空，则从star中获取“*”，将栈顶元素出栈，如果star也为空，则返回false；
 * 按照1 2 3 遍历完字符串之后，遍历left，与“*”匹配；
 * 如果left为空，返回true；
 * 如果left不为空，遍历left，从star栈顶出栈一个“”，如果“”的下标小于left的“（”的下标，那么返回false；如果大于，则left栈顶出栈，star栈顶出栈，进行下一轮比较；
 * 如果最后left还有值，star为空，返回false；如果left为空，star有值，返回true。
 *
 */
public class Test_678 {
    public boolean checkValidString(String s) {
        Stack<Integer> left = new Stack<>();
        Stack<Integer> star = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left.push(i);
            } else if (s.charAt(i) == '*') {
                star.push(i);
            } else if (s.charAt(i) == ')') {
                if (!left.isEmpty()) {
                    left.pop();
                } else if (!star.isEmpty()) {
                    star.pop();
                } else {
                    return false;
                }
            }
        }

        while (!left.isEmpty()) {
            if (star.isEmpty()) {
                return false;
            }
            if (left.peek() < star.peek()) {
                left.pop();
                star.pop();
            }else {
                return false;
            }
        }
        return true;
    }
}
