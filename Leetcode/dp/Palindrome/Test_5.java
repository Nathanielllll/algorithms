package dp.Palindrome;


/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 0 1 2
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 0 1 2 3
 * 输入: "cbbd"
 * 输出: "bb"
 *
 *
 * 使用中心扩散的方法，而不是马拉车算法
 */
public class Test_5 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    // dp i在前面，j在后面
    public static String longestPalindrome(String s) {
        int maxLen = 1;
        int begin = 0;
        int length = s.length();
        boolean[][] dp = new boolean[length][length];

        for (int j = 0; j < length; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i + 1 > maxLen) {
                        begin = i;
                        maxLen = j - i + 1;
                    }
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    // 中心扩散法1
    public String longestPalindrome1(String s) {
        // ababa 求最长公共子串
        int len = s.length();
        String result = "";

        for (int i = 0; i < len * 2 - 1; i++) {
            int left = i / 2;
            int right = left + i % 2;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                String tmp = s.substring(left, right + 1);
                if (tmp.length() > result.length()) {
                    result = tmp;
                }
                left--;
                right++;
            }
        }
        return result;
    }

    // 中心扩散法2
    public static String longestPalindrome2(String string) {
        if (string == null || string.length() < 1) {
            return null;
        }

        String resultString = "";
        int maxLen = 0;
        for (int i = 0; i < string.length(); i++) {
//            1、如果传入重合的索引编码，进行中心扩散，此时得到的最长回文子串的长度是奇数；
            int len1 = expandAroundCenter(string, i, i);
//            2、如果传入相邻的索引编码，进行中心扩散，此时得到的最长回文子串的长度是偶数。
            int len2 = expandAroundCenter(string, i, i + 1);
            int len = Math.max(len1, len2);

            if (maxLen < len) {
                maxLen = len;
                //奇数
                if (len1 > len2) {
                    resultString = string.substring(i - len / 2, i + len / 2 + 1);
                } else {
                    resultString = string.substring(i - len / 2 + 1, i + len / 2 + 1);
                }
            }
        }
        return resultString;
    }


    public static int expandAroundCenter(String string, int left, int right) {
        while (left >= 0 && right < string.length()
                && string.charAt(left) == string.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
