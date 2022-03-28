package slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串s和一个非空字符串p，找到s中所有是p的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串s和 p的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例1:
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
 * 示例 2:
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
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() < 1) {
            return result;
        }

        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            needs.put(ch, needs.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int match = 0;
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (needs.containsKey(rightChar)) {
                window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
                if (needs.get(rightChar).equals(window.get(rightChar))) {
                    ++match;
                }
            }
            ++right;

            while (right - left == p.length()) {
                if (match == needs.size()) {
                    result.add(left);
                }
                char leftChar = s.charAt(left);
                if (needs.containsKey(leftChar)) {
                    if (needs.get(leftChar).equals(window.get(leftChar))) {
                        --match;
                    }
                    window.put(leftChar, window.getOrDefault(leftChar, 0) - 1);
                }
                ++left;
            }
        }
        return result;
    }
}
