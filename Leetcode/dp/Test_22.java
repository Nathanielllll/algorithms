package dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出n代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出n = 3，生成结果为：
 * <p>
 * [ "((()))", "(()())", "(())()", "()(())", "()()()" ]
 * <p>
 * dp[i] = "(" + dp[p] + ")" + dp[q] 其中 p+q=i-1
 */
public class Test_22 {

  public List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    String string = "";
    generateParenthesisDfs(string, n, n, result);
    return result;
  }

  private void generateParenthesisDfs(String curString, int l, int r, List<String> result) {
    if (l == 0 && r == 0) {
      result.add(curString);
      return;
    }
    if (l > r) {
      return;
    }
    if (l > 0) {
      generateParenthesisDfs(curString + "(", l - 1, r, result);
    }
    if (r > 0) {
      generateParenthesisDfs(curString + ")", l, r - 1, result);
    }
  }

  public static List<String> generateParenthesis2(int n) {
    if (n < 0) {
      return null;
    }
    List<List<String>> DP = new LinkedList<>();

    List<String> dp_0 = new LinkedList<>();
    dp_0.add("");
    DP.add(dp_0);

    List<String> dp_1 = new LinkedList<>();
    dp_1.add("()");
    DP.add(dp_1);

    for (int i = 2; i <= n; i++) {
      List<String> dp_i = new LinkedList<>();
      for (int p = 0; p < i; p++) {
        int q = i - 1 - p;
        List<String> dp_p = DP.get(p);
        List<String> dp_q = DP.get(q);
        for (String string_p : dp_p) {
          for (String string_q : dp_q) {
            String temp = "(" + string_p + ")" + string_q;
            dp_i.add(temp);
          }
        }
      }
      DP.add(dp_i);
    }
    return DP.get(n);

  }
}
