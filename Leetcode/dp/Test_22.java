package dp;

import java.util.LinkedList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * dp[i] = "(" + dp[p] + ")" + dp[q] 其中 p+q=i-1
 */
public class Test_22 {

    public static List<String> generateParenthesis(int n) {
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
                for(String string_p : dp_p){
                    for(String string_q : dp_q){
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
