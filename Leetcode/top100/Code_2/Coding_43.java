package top100.Code_2;

import java.util.Stack;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * <p>
 * 【解题思路】：
 * 该算法是通过两数相乘时，乘数某位与被乘数某位相乘，与产生结果的位置的规律来完成。具体规律如下：
 * <p>
 * 乘数 num1 位数为 M，被乘数 num2 位数为 N, num1 x num2 结果 res 最大总位数为 M+N
 * num1[i] x num2[j] 的结果为 tmp(位数为两位，"0x","xy"的形式)，其第一位位于 res[i+j],第二位位于res[i+j+1]
 * 结合下图更容易理解
 */
public class Coding_43 {
    public static String example() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");

        return stack.get(0);
    }

    public static void main(String[] args) {
        System.out.println(example());
    }
}
