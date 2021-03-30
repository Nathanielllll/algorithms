package twoPointers;

import java.util.List;

/*
给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。
如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。

示例 1:

输入:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

输出:
"apple"
示例 2:

输入:
s = "abpcplea", d = ["a","b","c"]

输出:
"a"
 */
public class Test_524 {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for (String word : dictionary) {
            int ans_len = ans.length();
            int cur_len = word.length();
            // 条件二：ans.compareTo(word) < 0) ： ab和ba，需要返回ab
            if (ans_len > cur_len || (ans_len == cur_len && ans.compareTo(word) < 0)) {
                continue;
            }
            if (match(s, word)) {
                ans = word;
            }
        }
        return ans;
    }


    private static boolean match(String s1, String s2) {
        int i = 0;
        int j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            i++;
        }
        return i <= s1.length() && j == s2.length();
    }

    public static void main(String[] args) {
        String s2 = "abpcplea";
        String s1 = "apple";
        System.out.println(match(s1, s2));
    }
}
