package alibaba.March_27;
/*
给定字符串s1,s2，求从s1变为s2的最小移动次数（要求每一次只能从s1选取任意一个字符放到s1最后）

参考了讨论区大佬的思路，这道题实质上是求s1中能匹配s2的最长前缀，
剩下未匹配的字符只需要按照s2的顺序依次移动即可
时间复杂度：O(n)

*/
public class Test_1 {
    //检查s1能否变成s2
    public boolean check(String s1, String s2){
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] rec = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            rec[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2.length(); i++) {
            rec[s2.charAt(i) - 'a']--;
            if (rec[s2.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public int minChange(String s1, String s2){
        if (!check(s1, s2)) {
            return -1;
        }

//        //求最长的相同的子串长度
//        int length = s1.length();
//        int[][] dp = new int[length + 1][length + 1];
//        for (int i = 1; i <= length; i++) {
//            for (int j = 1; j <= length; j++) {
//                char c1 = s1.charAt(i - 1);
//                char c2 = s2.charAt(j - 1);
//                if (c1 == c2) {
//                    dp[i][j] = dp[i - 1][j - 1] + 1;
//                }else {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
//                }
//            }
//        }

        //统计出顺序上的相同的char的数目
        int cc = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(cc)) {
                cc++;
            }
        }
        return s2.length() - cc;
    }
}
