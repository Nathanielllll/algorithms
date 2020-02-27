package string;

import java.util.HashMap;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * 使用sunday算法
 * https://leetcode-cn.com/problems/implement-strstr/solution/python3-sundayjie-fa-9996-by-tes/
 */
public class Test_28 {
    //Sunday算法
    public static int strStr(String string, String pattern) {
        if (pattern == null || pattern.length() < 1) {
            return 0;
        }

        if (string == null || string.length() < 1 || string.length() < pattern.length()) {
            return -1;
        }

        int stringLen = string.length();
        int patternLen = pattern.length();

        int s_index = 0;//string的index

        //偏移表
        HashMap<Character, Integer> shift = new HashMap<>();
        for (int i = 0; i < patternLen; i++) {
            shift.put(pattern.charAt(i), patternLen - i);
        }

        //有等于号
        while (s_index + patternLen <= stringLen) {
            int nextCharIndex = s_index + patternLen;
            //匹配成功
            if (string.substring(s_index, nextCharIndex).equals(pattern)) {
                return s_index;
            } else {
                //若c存在于Pattern中，则 idx = idx + 偏移表[c]
                /**注意一定是nextCharacter < stringLen，如果是==，还怎么增加？*/
                //所以查看 string.substring(s_index, nextCharacter) 的下一个字符 k
                if (nextCharIndex < stringLen && shift.containsKey(string.charAt(nextCharIndex))) {
                    s_index += shift.get(string.charAt(nextCharIndex));
                //否则，idx = idx + len(pattern) + 1
                } else {
                    s_index += (patternLen + 1);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String string = "aaaaa";
        String pattern = "bba";
//        System.out.println(strStr(string, pattern));
//        System.out.println(strStr("a", "a"));

        System.out.println(strStr("hello", "ll"));
    }

}
