package slidingWindow;

import java.util.HashMap;
import java.util.Map;

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
        String s = "AAABABB";
        int k = 1;
        System.out.println(characterReplacement(s, k));
    }

    public static int characterReplacement(String s, int k) {
        int result = 0;
        int left = 0;
        int right = 0;
        int historyMaxCnt = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (right < s.length()) {
            char r_char = s.charAt(right);
            int r_cnt = window.getOrDefault(r_char, 0) + 1;
            window.put(r_char, r_cnt);
            historyMaxCnt = Math.max(historyMaxCnt, r_cnt);
            ++right;

            // 为什么只需要维护historyMaxCnt就可以了？因为我希望right - left尽可能大，也就是希望historyMaxCnt尽可能大。因此只需要记录历史上的historyMaxCnt即可
            // right - left - historyMaxCnt说明是需要替换的character数，只要<=k都是满足条件的，因此来计算result
            if (right - left - historyMaxCnt <= k) {
                result = Math.max(result, right - left);
            } else {
                // right - left - historyMaxCnt > k。说明需要替换的character数 > k了，也就是window太大了，需要缩小一下
                char l_char = s.charAt(left);
                window.put(l_char, window.get(l_char) - 1);
                ++left;
            }
        }
        return result;
    }
}
