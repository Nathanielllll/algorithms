package slidingWindow;

import java.util.HashMap;

/*
给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换k次。在执行上述操作后，找到包含重复字母的最长子串的长度。

注意：字符串长度 和 k 不会超过10^4。

示例 1：
输入：s = "ABAB", k = 2
输出：4
解释：用两个'A'替换为两个'B',反之亦然。

示例 2：
输入：s = "AABABBA", k = 1
输出：4
解释：
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class Test_424 {
    public static void main(String[] args) {
        String s = "AABABBA";
        int k = 1;
        System.out.println(characterReplacement(s, k));
    }

    public static int characterReplacement(String s, int k) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int result = 0;
        int length = s.length();
        while (right < length) {
            char right_char = s.charAt(right);
            int curCount = window.getOrDefault(right_char, 0) + 1;
            window.put(right_char, curCount);

            while (right - left + 1 > curCount + k) {
                char left_char = s.charAt(left);
                window.put(left_char, window.getOrDefault(left_char, 0) - 1);
                left++;
            }
            result = Math.max(result, right - left);
            right++;
        }
        return result;
    }
}
