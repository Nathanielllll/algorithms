import java.util.HashMap;
import java.util.Map;

public class Test_395 {
    /**
     * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串，c要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
     *
     * c
     *
     * 示例 1：
     *
     * 输入：s = "aaabb", k = 3
     * 输出：3
     * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
     * 示例 2：
     *
     * 输入：s = "ababbc", k = 2
     * 输出：5
     * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
     */

    public static int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        Map<Character, Integer> char2Cnt = new HashMap<>();
        for (char ch : s.toCharArray()) {
            char2Cnt.put(ch, char2Cnt.getOrDefault(ch, 0) + 1);
        }

        // 如果一个字符 c 在 s 中出现的次数少于 k 次，那么 s 中所有的包含 c 的子字符串都不能满足题意。
        // 所以，应该在 s 的所有不包含 c 的子字符串中继续寻找结果：把 s 按照 c 分割（分割后每个子串都不包含 c），得到很多子字符串 t；下一步要求 t 作为源字符串的时候，它的最长的满足题意的子字符串长度。
        // （到现在为止，我们把大问题分割为了小问题(s → t)）。此时我们发现，恰好已经定义了函数 longestSubstring(s, k) 就是来解决这个问题的！所以直接把 longestSubstring(s, k) 函数拿来用，于是形成了递归。
        for (char ch : char2Cnt.keySet()) {
            if (char2Cnt.get(ch) < k) {
                int result = 0;
                for (String str : s.split(String.valueOf(ch))) {
                    result = Math.max(result, longestSubstring(str, k));
                }
                return result;
            }
        }

        // 未进入递归时的返回结果：如果 s 中的每个字符出现的次数都大于 k 次，那么 s 就是我们要求的字符串，直接返回该字符串的长度。
        return s.length();
    }
}


