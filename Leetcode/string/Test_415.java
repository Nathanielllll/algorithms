package string;

/**
 * 给定两个字符串形式的非负整数num1 和num2，计算它们的和。
 * <p>
 * 注意：
 * <p>
 * num1 和num2的长度都小于 5100.
 * num1 和num2 都只包含数字0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库，也不能直接将输入的字符串转换为整数形式。
 */
public class Test_415 {
    public static String addStrings(String num1, String num2) {
        StringBuffer res = new StringBuffer();
        int carry = 0;
        for (int i = 0; i < Math.max(num1.length(), num2.length()); i++) {
            int sum = carry;
            if (i < num1.length()) {
                sum = sum + (num1.charAt(num1.length() - i - 1) - '0');
            }
            if (i < num2.length()) {
                sum = sum + (num2.charAt(num2.length() - i - 1) - '0');
            }
            res.append(sum % 10);
            carry = sum / 10;
        }

        if (carry != 0) {
            res.append(carry);
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addStrings("100","500"));
        System.out.println(addStrings("180","520"));
    }

}
