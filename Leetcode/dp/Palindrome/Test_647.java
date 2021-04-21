package dp.Palindrome;
/*
给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

示例 1：

输入："abc"
输出：3
解释：三个回文子串: "a", "b", "c"
示例 2：

输入："aaa"
输出：6
解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
public class Test_647 {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(countSubstrings_1(s));
    }

    // dp
    public static int countSubstrings(String s) {
        int result = 0;
        int length = s.length();
        boolean[][] dp = new boolean[length][length];

        for (int j = 0; j < length; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    result++;
                }
            }
        }
        return result;
    }

    // 中心扩散法
    public static int countSubstrings_1(String s) {
        int result = 0;
        int centerNums = s.length() * 2 - 1;
        for (int i = 0; i < centerNums; i++) {
            int left = i / 2;
            int right = left + i % 2;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                result++;
                left--;
                right++;
            }
        }
        return result;
    }


}
