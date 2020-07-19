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
    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(partition(s));
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> ans = new LinkedList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        Stack<String> stack = new Stack<>();

        backTracking(s, 0, stack, ans);
        return ans;
    }

    public static void backTracking(String s, int pos, Stack<String> stack, List<List<String>> ans){
        if (pos == s.length()) {
            List<String> list = new LinkedList<>(stack);
            ans.add(list);
            return;
        }

        for (int i = 1; i <= s.length(); i++) {
            if (pos + i > s.length()) {
                break;
            }

            String segment = s.substring(pos, pos + i);
            if (isPalindrome(segment)) {
                stack.push(segment);
                backTracking(s, pos + i, stack, ans);
                stack.pop();
            }
        }
    }

    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
