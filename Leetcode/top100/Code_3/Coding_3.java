package top100.Code_3;

import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * <p>
 * <p>
 * 使用动态规划? hashMap(或者数组来记录)
 */
public class Coding_3 {
    public static int lengthOfLongestSubstring(String s) {
        //字母及其出现的位置
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int curLength = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                int distance = i - hashMap.get(s.charAt(i));
                if(distance > curLength){
                    curLength++;
                }else {
                    curLength = distance;
                }
            }else {
                curLength++;
            }

            hashMap.put(s.charAt(i), i);
            maxLength = Math.max(curLength, maxLength);
        }
        return maxLength;

    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("abba"));
    }
}
