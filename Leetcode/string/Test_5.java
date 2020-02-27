package string;


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
    public static String longestPalindrome(String string) {
        if (string == null || string.length() < 1) {
            return null;
        }

        String resultString = "";
        int maxLen = 0;
        for (int i = 0; i < string.length(); i++) {
//            1、如果传入重合的索引编码，进行中心扩散，此时得到的最长回文子串的长度是奇数；
//            2、如果传入相邻的索引编码，进行中心扩散，此时得到的最长回文子串的长度是偶数。
            int len1 = expandAroundCenter(string, i, i);
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
