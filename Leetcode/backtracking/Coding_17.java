package backtracking;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class Coding_17 {

    static String[] letterMap = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    static List<String> result;

    public static List<String> letterCombinations(String digits) {
        result = new LinkedList<>();

        if (digits.equals("")) {
            return result;
        }
        Stack<Character> stack = new Stack<>();
        subProcess(digits, 0, stack);
        return result;
    }

    public static void subProcess(String digits, int len, Stack<Character> stack){
        if (stack.size() == digits.length()) {
            String res = "";
            for (char c : stack){
                res += c;
            }
            result.add(res);
            return;
        }

        String value = letterMap[digits.charAt(len) - '0'];
        for (int i = 0; i < value.length() && stack.size() <= digits.length(); i++) {
            stack.push(value.charAt(i));
            subProcess(digits, len + 1, stack);
            stack.pop();
        }

    }

    public static void main(String[] args) {
        String digits = "23";
        System.out.println(letterCombinations(digits));
    }
}
