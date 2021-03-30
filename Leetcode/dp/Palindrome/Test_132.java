package dp.Palindrome;
/*
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回符合要求的最少分割次数。

示例:

输入:"aab"
输出: 1
解释: 进行一次分割就可将s 分割成 ["aa","b"] 这样两个回文子串。
 */
public class Test_132 {
    public static void main(String[] args) {
        String s = "aab";
        System.out.println(minCut(s));
    }

    // i在前面，j在后面
    public static int minCut(String s) {
        int length = s.length();
        boolean[][] isPalindrome = new boolean[length][length];
        for (int j = 0; j < length; j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalindrome[i + 1][j - 1])) {
                    isPalindrome[i][j] = true;
                }
            }
        }

        int[] dp = new int[length];
        for (int j = 0; j < length; j++) {
            dp[j] = j;
        }

        for (int j = 0; j < length; j++) {
            if(isPalindrome[0][j]){
                dp[j] = 0;
                continue;
            }

            for (int i = 0; i < j; i++) {
                if(isPalindrome[i + 1][j]){
                    dp[j] = Math.min(dp[j], dp[i] + 1);
                }
            }
        }
        return dp[length - 1];
    }



}
