package dp.Palindrome;


/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设s 的最大长度为 1000。
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
        String result = "";
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    result = result.length() > j - i + 1 ? result : s.substring(i, j + 1);
                }
            }
        }
        return result;
    }

    // 中心扩散法1
    public String longestPalindrome01(String s) {
        // ababa 求最长公共子串
        int len = s.length();
        String result = "";

        for (int i = 0; i < len; i++) {
            String str1 = expandAroundCenter(s, i, i);
            String str2 = expandAroundCenter(s, i, i + 1);
            String str = str1.length() > str2.length() ? str1 : str2;
            result = result.length() > str.length() ? result : str;
        }
        return result;
    }

    public String expandAroundCenter(String string, int left, int right) {
        while (left >= 0 && right < string.length()
                && string.charAt(left) == string.charAt(right)) {
            left--;
            right++;
        }
        return string.substring(left + 1, right);
    }
}
