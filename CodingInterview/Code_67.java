/**
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 */
public class Code_67 {
    public int strToInt(String str) {
        boolean isBeginning = false;
        boolean isPositive = false;

        int index = 0;
        int result = 0;

        while (index < str.length()) {
            if (!isBeginning) {
                char c = str.charAt(index);
                if (c == ' ') {
                    index++;
                } else if (c == '+') {
                    isPositive = true;
                    isBeginning = true;
                    index++;
                } else if (c == '-') {
                    isPositive = false;
                    isBeginning = true;
                    index++;
                } else if (isNumber(c)) {
                    isPositive = true;
                    isBeginning = true;
                    result += c - '0';
                    index++;
                } else {
                    return 0;
                }
            } else {
                char c = str.charAt(index);
                if (!isNumber(c)) {
                    break;
                } else {
                    if (isPositive && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && c > '7'))) {
                        return Integer.MAX_VALUE;
                    }
                    if (!isPositive && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && c > '8'))) {
                        return Integer.MIN_VALUE;
                    }

                    result = result * 10 + (c - '0');
                    index++;
                }
            }
        }

        if (!isPositive) {
            result = -result;
        }
        return result;
    }

    public boolean isNumber(char c) {
        int value = c - '0';
        if (value >= 0 && value <= 9) {
            return true;
        } else {
            return false;
        }
    }
}
