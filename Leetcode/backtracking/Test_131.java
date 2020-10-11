package backtracking;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 *
 * 就是很经典的回溯法，一个 for 循环，添加元素，递归，删除元素。这里判断是否是回文串，我们就直接用 dp 数组。
 */
public class Test_131 {
    List<List<String>> ans;
    public List<List<String>> partition(String s) {
        ans = new LinkedList<>();
        Stack<String> stack = new Stack<>();

        backTracking(s, stack, 0);
        return ans;
    }

    private void backTracking(String s, Stack<String> stack, int pos){
        if (pos == s.length()) {
            List<String> list = new LinkedList<>();
            for(String str : stack){
                list.add(str);
            }
            ans.add(list);
        }

        for (int i = 1; i <= s.length() && i + pos <= s.length(); i++) {
            String segment = s.substring(pos, i + pos);

            if (isPalindrome(segment)) {
                stack.push(segment);
                backTracking(s, stack, i + pos);
                stack.pop();
            }
        }
    }

    public static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
