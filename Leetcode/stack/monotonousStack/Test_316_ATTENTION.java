package stack.monotonousStack;

import java.util.HashMap;
import java.util.Stack;

/*
316. 去除重复字母
给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同

示例 1：
输入：s = "bcabc"
输出："abc"

示例 2：
输入：s = "cbacdcbc"
输出："acdb"


 */
public class Test_316_ATTENTION {
    public static void main(String[] args) {
        String str = "cbacdcbc";
        System.out.println(removeDuplicateLetters(str));
    }

    public static String removeDuplicateLetters(String s) {
        HashMap<Character, Integer> lastIndex = new HashMap<>();
        HashMap<Character, Boolean> visited = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndex.put(s.charAt(i), i);
            visited.put(s.charAt(i), false);
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (visited.get(ch)) {
                continue;
            }

            while (!stack.isEmpty() && stack.peek() > ch && lastIndex.get(stack.peek()) > i) {
                visited.put(stack.pop(), false);
            }

            stack.push(ch);
            visited.put(ch, true);
        }

        StringBuffer resultBuffer = new StringBuffer();
        for(char c : stack){
            resultBuffer.append(c);
        }
        return resultBuffer.toString();
    }
}
