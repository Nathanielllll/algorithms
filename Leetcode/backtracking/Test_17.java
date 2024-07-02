package backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入："23" 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class Test_17 {

  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();
    if (digits.isEmpty()) {
      return res;
    }
    Map<Character, String> digitToChar = new HashMap<>();
    digitToChar.put('2', "abc");
    digitToChar.put('3', "def");
    digitToChar.put('4', "ghi");
    digitToChar.put('5', "jkl");
    digitToChar.put('6', "mno");
    digitToChar.put('7', "qprs");
    digitToChar.put('8', "tuv");
    digitToChar.put('9', "wxyz");
    Stack<Character> stack = new Stack<>();
    letterCombinationsDfs(digits, digitToChar, stack, res, 0);
    return res;
  }

  // idx是对digits来说的！
  private void letterCombinationsDfs(String digits, Map<Character, String> digitToChar,
      Stack<Character> stack, List<String> result, int idx) {
    if (idx == digits.length()) {
      StringBuilder res = new StringBuilder();
      for (char c : stack) {
        res.append(c);
      }
      result.add(res.toString());
      return;
    }

    char digit = digits.charAt(idx);
    String str = digitToChar.get(digit);
    for (int i = 0; i < str.length(); i++) {
      stack.push(str.charAt(i));
      letterCombinationsDfs(digits, digitToChar, stack, result, idx + 1);
      stack.pop();
    }
  }
}
