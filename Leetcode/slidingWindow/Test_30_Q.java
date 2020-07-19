package slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 */

//为什么// 由于字符串不一定是单词长度的倍数，所以需要遍历一个单词长度的所有情况
//        for (int i = 0; i < len; i++) {
//            int left = i;
//            int right = i;
//            int match = 0;
//   并且需要清空window？？
public class Test_30_Q {
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (words == null || words.length < 1) {
            return result;
        }

        //一些长度相同的单词 words, 因此words里面的word的len是相同
        int len = words[0].length();


        // 定义两个map集合，一个存目标单词，一个存滑动窗口
        HashMap<String, Integer> needs = new HashMap<>();
        HashMap<String, Integer> window = new HashMap<>();

        // 初始化目标集合，将单词与出现的次数对应存入map集合中
        for (int i = 0; i < words.length; i++) {
            needs.put(words[i], needs.getOrDefault(words[i], 0) + 1);
        }

        // 这里有个需要注意的点，因为字符串不一定是单词长度的倍数，
        // 所以指针开始滑动的位置不能只有0，而是需要考虑整个单词长度的情况。
        for (int i = 0; i < len; i++) {
            int left = i;
            int right = i;
            int match = 0;

            while (right <= s.length() - len) {
                String string_right = s.substring(right, right + len);
                if (needs.containsKey(string_right)) {
                    window.put(string_right, window.getOrDefault(string_right, 0) + 1);
                    if (window.get(string_right).equals(needs.get(string_right))) {
                        match++;
                    }
                }
                right += len;

                // 当匹配数等于目标集合的大小（说明已经覆盖了目标集合）
                while (match == needs.size()) {
                    // right - left / len求出窗口中单词数，如果等于目标单词数，则匹配成功，将左指针位置加入list
                    //因为题目要求：注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
                    if ((right - left) / len == words.length) {
                        result.add(left);
                    }

                    // 左指针右移，类似右指针方法
                    String string_left = s.substring(left, left + len);
                    if (needs.containsKey(string_left)) {
                        window.put(string_left, window.getOrDefault(string_left, 0) - 1);
                        if (window.get(string_left) < needs.get(string_left)) {
                            match--;
                        }
                    }
                    left += len;
                }
            }
            window.clear();
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"bar", "foo"};
        System.out.println(findSubstring(s, words));

        String s1 = "aaaaaaaa";
        String[] words1 ={"aa","aa","aa"};
        System.out.println(findSubstring(s1, words1));
    }
}
