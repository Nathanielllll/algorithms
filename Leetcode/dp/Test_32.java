package dp;

import java.util.Stack;

/**
 * 给定一个只包含 '('和 ')'的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * 我们定义一个 dp 数组，其中第 i 个元素表示以下标为 i 的字符结尾的最长有效子字符串的长度。
 * ()(()()  )
 */
public class Test_32 {

    // dp
    public static int longestValidParentheses(String s) {
        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (s.charAt(i - 1) == ')') {
                    int preIndex = i - dp[i - 1] - 1;
                    if (preIndex >= 0 && s.charAt(preIndex) == '(') {
                        dp[i] = (preIndex - 1 >= 0 ? dp[preIndex - 1] : 0) + dp[i - 1] + 2;
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // 栈
    public int longestValidParentheses_1(String s) {
        int max = 0;
        Stack<Integer> indexStack = new Stack<>();
        indexStack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                indexStack.push(i);
            } else if (c == ')') {
                indexStack.pop();
                if (indexStack.isEmpty()) {
                    indexStack.push(i);
                } else {
                    max = Math.max(max, i - indexStack.peek());
                }
            }
        }
        return max;
    }
}
