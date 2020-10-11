package backtracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Test_140 {
    /*
    140. 单词拆分 II
    给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。

    说明：

    分隔时可以重复使用字典中的单词。
    你可以假设字典中没有重复的单词。
    示例 1：

    输入:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    输出:
    [
      "cats and dog",
      "cat sand dog"
    ]

    https://leetcode-cn.com/problems/word-break-ii/solution/javadfsjian-zhi-xie-fa-lei-si-shang-yi-ge-dan-ci-c/
     */

    List<String> ans;
    //剪枝：这里用boolean[] cantBreak的cantBreak[start]标识以start作为下标的子串不能在接下去的DFS完成字符串匹配。
    boolean[] cantBreak;

    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> hashSet = new HashSet<>(wordDict);
        Stack<String> stack = new Stack<>();
        ans = new LinkedList<>();
        cantBreak = new boolean[s.length()];

        backTracking(s, wordDict, stack, 0);
        return ans;
    }

    private void backTracking(String s, List<String> wordDict, Stack<String> stack, int pos){
        int resCurLen = ans.size();

        if (pos == s.length()) {
            String sentence = "";
            for(String word : stack){
                sentence += word + " ";
            }
            ans.add(sentence.trim());
            return;
        }

        if(cantBreak[pos]) return;

        for (int i = 1; i <= s.length() && i + pos <= s.length(); i++) {
            String segment = s.substring(pos, pos + i);

            if (wordDict.contains(segment)) {
                stack.push(segment);
                backTracking(s, wordDict, stack, pos + i);
                stack.pop();
            }
        }
        cantBreak[pos] = ans.size() <= resCurLen;
    }
}
