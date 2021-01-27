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
public class Test_316 {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>(); // 单调栈，存放字符
        char[] charArray = s.toCharArray();
        boolean[] visited = new boolean[26]; // 记录该字符是否在单调栈当中
        int[] lastIndex = new int[26]; // 存放该字符最后出现的位置
        for (int i = 0; i < charArray.length; i++) {
            int index = charArray[i] - 'a';
            lastIndex[index] = i;
        }

        for (int i = 0; i < charArray.length; i++) {
            if(visited[charArray[i] - 'a']){
                continue;
            }

            while(!stack.isEmpty() && stack.peek() > charArray[i] && lastIndex[stack.peek() - 'a'] > i){
                char c = stack.pop();
                visited[c - 'a'] = false;
            }

            stack.push(charArray[i]);
            visited[charArray[i] - 'a'] = true;
        }
        StringBuffer resultBuffer = new StringBuffer();
        for(char c : stack){
            resultBuffer.append(c);
        }
        return resultBuffer.toString();
    }
}
