package stack;

import java.util.Stack;

/*
给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。

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
        if (s == null) {
            return "";
        }
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            String c = s.substring(i, i + 1);
            if (!c.equals("]")) {
                stack.push(c);
            }else {
                StringBuffer stringBuffer = new StringBuffer();
                while (!stack.isEmpty() && !stack.peek().equals("[")) {
                    stringBuffer.append(stack.pop());
                }
                //将'[' pop出来
                stack.pop();

                StringBuffer times_string = new StringBuffer();
                while (!stack.isEmpty() && stack.peek().length() == 1 && isNum(stack.peek().charAt(0))) {
                    times_string.append(stack.pop());
                }
                int times = Integer.parseInt(times_string.reverse().toString());

                String string = stringBuffer.toString();
                for (int j = 0; j < times; j++) {
                    stack.push(string);
                }
            }
        }

        StringBuffer result = new StringBuffer();
        while(!stack.isEmpty()){
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    private static boolean isNum(char c){
        if (c >= '0' && c <= '9') {
            return true;
        }else {
            return false;
        }
    }

}
