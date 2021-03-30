package backtracking;

import java.util.*;

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

    List<String> result;
    Stack<String> stack;

    public List<String> wordBreak(String s, List<String> wordDict) {
        result = new ArrayList<>();
        stack = new Stack<>();

        backTracking(s, wordDict, 0);
        return result;
    }

    private void backTracking(String string, List<String> wordDict, int pos) {
        if (pos == string.length()) {
            String cur = String.join(" ", stack);
            result.add(cur);
            return;
        }

        for (int i = 1; i <= string.length(); i++) {
            if (pos + i > string.length()) {
                break;
            }

            String segment = string.substring(pos, pos + i);
            if (wordDict.contains(segment)) {
                stack.push(segment);
                backTracking(string, wordDict, pos + i);
                stack.pop();
            }
        }
    }
}
