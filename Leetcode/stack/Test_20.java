package stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。 左括号必须以正确的顺序闭合。 注意空字符串可被认为是有效字符串。
 */
public class Test_20 {

  public static boolean isValid(String s) {
    if (s.length() % 2 == 1) {
      return false;
    }
    HashMap<Character, Character> hashMap = new HashMap<>();
    hashMap.put('(', ')');
    hashMap.put('{', '}');
    hashMap.put('[', ']');

    int length = s.length();

    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < length; i++) {
      char ch = s.charAt(i);
      if (hashMap.containsKey(ch)) {
        stack.push(ch);
      } else {
        // stack里面必须要有一个'('和当前的')'对应。因此stack.isEmpty()则返回false
        if (!stack.isEmpty() && ch == hashMap.get(stack.peek())) {
          stack.pop();
        } else {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
}
