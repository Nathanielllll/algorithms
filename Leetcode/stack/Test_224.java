package stack;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author luzhi
 * @date 2021/3/11
 */
public class Test_224 {
    public int calculate(String s) {
        // 使用 map 维护一个运算符优先级
        // 这里的优先级划分按照「数学」进行划分即可
        Map<Character, Integer> map = new HashMap<>();
        map.put('-', 1);
        map.put('+', 1);
        map.put('*', 2);
        map.put('/', 2);
        map.put('%', 2);
        map.put('^', 3);

        // 将所有的空格去掉，并将 (- 替换为 (0-
        s = s.replaceAll(" ", "");
        s = s.replaceAll("\\(-", "(0-");
        s = s.replaceAll("\\(\\+", "(0+");
        char[] cs = s.toCharArray();
        int n = s.length();
        // 存放所有的数字
        Stack<Integer> nums = new Stack<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.push(0);
        // 存放所有「非数字以外」的操作
        Stack<Character> ops = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    if (ops.peek() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pop();
                        break;
                    }
                }
            } else if (isNumber(c)) {// 如果是数字
                int u = 0;
                int j = i;
                // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                while (j < n && isNumber(cs[j])) u = u * 10 + (cs[j++] - '0');
                nums.push(u);
                i = j - 1;
            } else { // 如果是运算符
                // 有一个新操作要入栈时，先把栈内可以算的都算了
                // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                while (!ops.isEmpty() && ops.peek() != '(') {
                    char prev = ops.peek();
                    if (map.get(prev) >= map.get(c)) {
                        calc(nums, ops);
                    } else {
                        break;
                    }
                }
                ops.push(c);
            }
        }

        // 将剩余的计算完
        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peek();
    }

    void calc(Stack<Integer> nums, Stack<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pop(), a = nums.pop();
        char op = ops.pop();
        int ans = 0;
        if (op == '+') ans = a + b;
        else if (op == '-') ans = a - b;
        else if (op == '*') ans = a * b;
        else if (op == '/') ans = a / b;
        else if (op == '^') ans = (int) Math.pow(a, b);
        else if (op == '%') ans = a % b;
        nums.push(ans);
    }

    boolean isNumber(char c) {
        return Character.isDigit(c);
    }
}
