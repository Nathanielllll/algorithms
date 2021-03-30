package dfs;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author luzhi
 * @date 2021/3/30
 */
public class Test_241 {
    /*
    给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +,-以及*。

    示例1:

    输入: "2-1-1"
    输出: [0, 2]
    解释:
    ((2-1)-1) = 0
    (2-(1-1)) = 2
    示例2:

    输入: "2*3-4*5"
    输出: [-34, -14, -10, -10, 10]
    解释:
    (2*(3-(4*5))) = -34
    ((2*3)-(4*5)) = -14
    ((2*(3-4))*5) = -10
    (2*((3-4)*5)) = -10
    (((2*3)-4)*5) = 10
     */

    public static void main(String[] args) {
        String str = "2-1-1";
        System.out.println(diffWaysToCompute(str));
    }

    public static List<Integer> diffWaysToCompute(String expression) {
        HashSet<Character> operations = new HashSet<>();
        operations.add('+');
        operations.add('-');
        operations.add('*');

        return dfs(expression, operations);
    }

    private static List<Integer> dfs(String expression, HashSet<Character> operations) {
        if (isNumeric(expression)) {
            List<Integer> list = new ArrayList<>();
            list.add(Integer.valueOf(expression));
            return list;
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (operations.contains(ch)) {
                List<Integer> left = dfs(expression.substring(0, i), operations);
                List<Integer> right = dfs(expression.substring(i + 1), operations);
                for (int l : left) {
                    for (int r : right) {
                        int ans = 0;
                        if (ch == '+') {
                            ans = l + r;
                        } else if (ch == '-') {
                            ans = l - r;
                        } else if (ch == '*') {
                            ans = l * r;
                        }
                        result.add(ans);
                    }
                }
            }
        }
        return result;
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
