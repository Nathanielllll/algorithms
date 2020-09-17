package slidingWindow;

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
 *
 */
public class Test_3 {
    public static int lengthOfLongestSubstring(String s) {
        int result = 0;

        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            /*在最后计算result的时候，一定能保证window里面的char的值为1，也就整个window里面的char可以得到：无重复字符的子串*/
            Character character_right = s.charAt(right);
            window.put(character_right, window.getOrDefault(character_right, 0) + 1);
            right++;

            //表示有重复的c_right，移动left来去重
            while (window.get(character_right) > 1) {
                Character character_left = s.charAt(left);
                //其实大可不必用getOrDefault，因为肯定>=1。因为这个character_left，character_right早就遍历并且包括在里面了
                window.put(character_left, window.getOrDefault(character_left, 0) - 1);
                left++;
            }
            result = Math.max(result, right - left);
        }
        return result;
    }
}
