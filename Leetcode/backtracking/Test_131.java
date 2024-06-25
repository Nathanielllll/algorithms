package backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 * <p>
 * 示例:
 * <p>
 * 输入:"aab" 输出: [ ["aa","b"], ["a","a","b"] ]
 * <p>
 * <p>
 * 就是很经典的回溯法，一个 for 循环，添加元素，递归，删除元素。这里判断是否是回文串，我们就直接用 dp 数组。
 */
public class Test_131 {

  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    Stack<String> stack = new Stack<>();
    partitionDfs(s, 0, result, stack);
    return result;
  }

  private void partitionDfs(String s, int pos, List<List<String>> result, Stack<String> stack) {
    if (pos == s.length()) {
      result.add(new ArrayList<>(stack));
      return;
    }

    for (int i = pos; i < s.length(); i++) {
      if (isPalindrome(s, pos, i)) {
        stack.add(s.substring(pos, i + 1));
        // 需要特别注意，这里的pos值是i + 1。说明已经使用过的字母，就不能再被使用了
        partitionDfs(s, i + 1, result, stack);
        stack.pop();
      }
    }
  }

  private boolean isPalindrome(String str, int l, int r) {
    while (l < r) {
      if (str.charAt(l) != str.charAt(r)) {
        return false;
      }
      ++l;
      --r;
    }
    return true;
  }
}
