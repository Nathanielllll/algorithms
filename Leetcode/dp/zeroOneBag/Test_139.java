package dp.zeroOneBag;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class Test_139 {
    //求解顺序的完全背包问题时，对物品的迭代应该放在最里层，对背包的迭代放在外层，只有这样才能让物品按一定顺序放入背包中。
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (String word : wordDict){
                int len = word.length();
                /**那也要这个单词符合才可以啊！才能选择 要or不要*/
                if (i >= len && word.equals(s.substring(i - len, i))) {
                    dp[i] = dp[i] || dp[i - len];
                }
            }
        }

        //下面这个是错误的，上面的才是正确的！
        // 验证了：求解顺序的完全背包问题时，对物品的迭代应该放在最里层，对背包的迭代放在外层，只有这样才能让物品按一定顺序放入背包中。
//        for (String word : wordDict){
//            for (int i = 1; i <= n; i++) {
//                int len = word.length();
//                /**那也要这个单词符合才可以啊！才能选择 要/不要*/
//                if (i >= len && word.equals(s.substring(i - len, i))) {
//                    dp[i] = dp[i] || dp[i - len];
//                }
//            }
//        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList(new String[]{"apple","pen"});
        System.out.println(wordBreak(s, wordDict));
    }
}
