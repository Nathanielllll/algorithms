package stack;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author luzhi
 * @date 2021/3/11
 */
public class Test_227 {

    /*
    重点：
    1. 有 +，-，*，/四种运算符；
    2. 没有括号。


    既然包含 4 种运算符，那么就有运算符优先级的问题：先 *，/，再 +，-。本题解的思路就是把所有的 *，/先计算出来，最后计算只有 +，-运算符的表达式。

    使用一个「栈」只保存需要进行 +，-运算符的所有数字（把-运算符改成负数放到栈中）。

    如果遇到 *，/运算，则需要把结果先计算出来，也放到栈里；

    在把所有乘除法计算完成之后，最后对栈内数字求和。

    但是注意栈顶元素由于后面可能遇到 *，/运算符，所以栈顶元素有可能还会被弹出来，跟后面的运算符做计算。

    思路是不是很简单？

    具体实现的时候，按照下面的思路来考虑。

    一个运算符表达式分为三个部分，可以用下面的情况表示：

    数字①， 运算符②， 数字③
    数字①，运算符②，数字③

    数字①，在栈中保存，为栈顶的元素；
    运算符②，用一个变量 pre_op 保存；
    数字③，用一个变量 num 保存。
    操作情况：
    运算符②，决定了现在的操作：

    如果 运算符② 为 +，-：如果是+，是把数字③入栈；如果 -，是把 数字③取反入栈。
    如果 运算符② 为 *，/，则需要计算 数字① 运算符② 数字③，然后把结果 入栈。
    这样遍历一次后，优先把所有的 *，/都计算出来，而且与需要做加减运算的数字一起，全都都放到了栈中，对栈求和，即为最终的结果。
     */
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char preSign = '+';
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                num = num * 10 + chars[i] - '0';
            }
            if (!Character.isDigit(chars[i]) && chars[i] != ' ' || i == chars.length - 1) {
                if (preSign == '+') {
                    stack.push(num);
                } else if (preSign == '-') {
                    stack.push(-num);
                } else if (preSign == '*') {
                    stack.push(stack.pop() * num);
                } else if (preSign == '/') {
                    stack.push(stack.pop() / num);
                }
                preSign = chars[i];
                num = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    public int calculate_1(String s) {
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
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);
        // 存放所有「非数字以外」的操作
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNumber(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < n && isNumber(cs[j])) u = u * 10 + (cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.addLast(c);
                }
            }
        }
        // 将剩余的计算完
        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peekLast();
    }

    void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;
        if (op == '+') ans = a + b;
        else if (op == '-') ans = a - b;
        else if (op == '*') ans = a * b;
        else if (op == '/') ans = a / b;
        else if (op == '^') ans = (int) Math.pow(a, b);
        else if (op == '%') ans = a % b;
        nums.addLast(ans);
    }

    boolean isNumber(char c) {
        return Character.isDigit(c);
    }
}
