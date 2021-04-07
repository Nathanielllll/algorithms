package stack;

import java.util.Stack;

/**
 * @author luzhi
 * @date 2021/4/6
 */
public class Test_1209 {
    /*
    给你一个字符串s，「k 倍重复项删除操作」将会从 s中选择k个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。

    你需要对s重复进行无限次这样的删除操作，直到无法继续为止。

    在执行完所有删除操作后，返回最终得到的字符串。

    本题答案保证唯一。

    示例 1：

    输入：s = "abcd", k = 2
    输出："abcd"
    解释：没有要删除的内容。
    示例 2：

    输入：s = "deeedbbcccbdaa", k = 3
    输出："aa"
    解释：
    先删除 "eee" 和 "ccc"，得到 "ddbbbdaa"
    再删除 "bbb"，得到 "dddaa"
    最后删除 "ddd"，得到 "aa"
    示例 3：

    输入：s = "pbbcggttciiippooaais", k = 2
    输出："ps"
     */
    public static String removeDuplicates(String s, int k) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String segment = s.substring(i, i + 1);
            if (stack.isEmpty()) {
                stack.push(segment);
            } else {
                if (stack.peek().substring(0, 1).equals(segment)) {
                    if (stack.peek().length() + 1 >= k) {
                        stack.pop();
                    } else {
                        stack.push(stack.pop() + segment);
                    }
                } else {
                    stack.push(segment);
                }
            }
        }

        StringBuffer resultBuffer = new StringBuffer();
        for (String str : stack) {
            resultBuffer.append(str);
        }
        return resultBuffer.toString();
    }
}
