package string;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * <p>
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
public class Test_8_ATTENTION {
    public static int myAtoi(String str) {
        //正负数的标志
        boolean isPositive = false;

        //是否开始的标志
        boolean isBeginning = false;
        int index = 0;
        int result = 0;
        while (index < str.length()) {
            //还未开始
            if (!isBeginning) {
                //空格
                if (str.charAt(index) == ' ') {
                    index++;
                }
                //正号
                else if (str.charAt(index) == '+') {
                    isBeginning = true;
                    isPositive = true;
                    index++;
                }
                //负号
                else if (str.charAt(index) == '-') {
                    isBeginning = true;
                    isPositive = false;
                    index++;
                }
                //数字
                else if (charIsNum(str.charAt(index))) {
                    isBeginning = true;
                    isPositive = true;
                    result += (str.charAt(index) - '0');
                    index++;
                } else {
                    return 0;
                }
            //已经开始
            } else {
                if (charIsNum(str.charAt(index))) {
                    if (isPositive && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && str.charAt(index) > '7'))) {
                        return Integer.MAX_VALUE;
                    }
                    //这里是判断负数越界。
                    if (!isPositive && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && str.charAt(index) > '8'))) {
                        return Integer.MIN_VALUE;
                    }
                    result = result * 10 + (str.charAt(index) - '0');
                    index++;
                } else {
                    break;
                }
            }
        }

        if (!isPositive) {
            result = -result;
        }

        return result;
    }

    public static boolean charIsNum(Character character) {
        int value = character - '0';
        if (value <= 9 && value >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//        输入: "-91283472332"
//                * 输出: -2147483648
        System.out.println(myAtoi("-91283472332"));
    }


}
