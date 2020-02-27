package slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 */
public class Test_438 {
    public static List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() < 1) {
            return result;
        }

        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> needs = new HashMap<>();
        //字符串target放入hashMap
        for (int i = 0; i < t.length(); i++) {
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }

        //滑动窗口的双指针
        int left = 0;
        int right = 0;
        //匹配的个数
        int match = 0;

        while (right < s.length()) {
            // right对应的character
            Character character_right = s.charAt(right);
            if (needs.containsKey(character_right)) {
                //可能：window当中的character_left >= needs当中的character_left
                window.put(character_right, window.getOrDefault(character_right, 0) + 1);

                /**注意！！！不能直接用==*/
                //character匹配的时候
                if (window.get(character_right).equals(needs.get(character_right))) {
                    match++;
                }
            }
            right++;

            //当right达到符合条件的最右边
            while (match == needs.size()) {
                //更新result
                if (right - left == t.length()) {
                    result.add(left);
                }

                Character character_left = s.charAt(left);
                //如果包含character_left，window减少
                if (needs.containsKey(character_left)) {
                    //window当中的character_left >= needs当中的character_left
                    window.put(character_left, window.getOrDefault(character_left, 0) - 1);
                    //不再相等的时候
                    if (window.get(character_left) < needs.get(character_left)) {
                        match--;
                    }
                }
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("abab", "ab"));
        System.out.println(findAnagrams("cbaebabacd","abc"));
        System.out.println(Integer.MAX_VALUE);
    }
}
