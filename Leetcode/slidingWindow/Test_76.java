package slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC" 输出: "BANC" 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * <p>
 * <p>
 * 滑动窗口算法的思路是这样：
 * <p>
 * 1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，把索引闭区间 [left, right] 称为一个「窗口」。
 * <p>
 * 2、我们先不断地增加 right 指针扩大窗口 [left, right]，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
 * <p>
 * 3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right]，直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，每次增加
 * left，我们都要更新一轮结果。
 * <p>
 * 4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
 */
public class Test_76 {

  public String minWindow(String s, String t) {
    if (t.length() > s.length()) {
      return "";
    }
    if (t.length() == s.length() && s.equals(t)) {
      return s;
    }
    Map<Character, Integer> window = new HashMap<>();
    Map<Character, Integer> needs = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      char ch = t.charAt(i);
      needs.put(ch, needs.getOrDefault(ch, 0) + 1);
    }

    char[] chars = s.toCharArray();
    int left = 0;
    int right = 0;
    int n = chars.length;
    int match = 0;
    int minLength = Integer.MAX_VALUE;
    String result = "";
    while (right < n) {
      char rightChar = chars[right];
      if (needs.containsKey(rightChar)) {
        window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
        if (window.get(rightChar).equals(needs.get(rightChar))) {
          ++match;
        }
      }
      ++right;

      while (match == needs.size()) {
        if (right - left < minLength) {
          minLength = right - left;
          result = s.substring(left, right);
        }
        char leftChar = chars[left];
        if (needs.containsKey(leftChar)) {
          window.put(leftChar, window.getOrDefault(leftChar, 0) - 1);
          if (needs.get(leftChar) > window.get(leftChar)) {
            --match;
          }
        }
        ++left;
      }
    }
    return result;
  }


}
