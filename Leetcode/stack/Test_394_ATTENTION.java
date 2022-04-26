package stack;

import java.util.Stack;

/*
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。

示例:

s = "3[a]2[bc]", 返回 "aaabcbc".
s = "3[a2[c]]", 返回 "accaccacc".
s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".

*/
public class Test_394_ATTENTION {
    public static void main(String[] args) {
        String s = "11[ab]";//abcabccdcdcdef
        System.out.println(decodeString(s));
    }

    public static String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            String str = s.substring(i, i + 1);
            if (!str.equals("]")) {
                stack.push(str);
            } else {
                // 需要复制的字符串（倒序）
                StringBuilder sb1 = new StringBuilder();
                while (!stack.isEmpty() && !stack.peek().equals("[")) {
                    sb1.append(stack.pop());
                }
                // 弹出[
                stack.pop();

                // 需要复制的次数（倒序）
                StringBuilder sb2 = new StringBuilder();
                while (!stack.isEmpty() && isNumber(stack.peek())) {
                    sb2.append(stack.pop());
                }

                // 只对复制的次数sb做倒转
                int times = Integer.parseInt(sb2.reverse().toString());

                for (int j = 0; j < times; j++) {
                    stack.push(sb1.toString());
                }
            }
        }

        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()){
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
