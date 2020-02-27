package top100.Code_5;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 *       0 1 2
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 *      0 1 2 3
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class Coding_5 {
    public static String longestPalindrome(String string) {
        if (string == null || string.length() < 1) {
            return null;
        }

        int start = 0, end = 0;

        for (int i = 0; i < string.length(); i++) {
            //因为string的长度可能是奇数或者是偶数
            int len1 = expandAroundCenter(string, i, i);
            int len2 = expandAroundCenter(string, i, i + 1);
            int len = Math.max(len1, len2);

            /**一些不能明白的细节*/
            int maxLen = end - start;
            if (len > maxLen) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return string.substring(start, end+1);
    }


    public static int expandAroundCenter(String string, int left, int right) {
        int L = left;
        int R = right;
        while (L >= 0 && R < string.length() && string.charAt(L) == string.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
    }
}
