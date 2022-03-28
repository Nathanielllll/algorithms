package string;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字1和0。
 *
 * 示例1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class Test_67 {
    public String addBinary(String a, String b) {
        if (a.equals("0")) {
            return b;
        }
        if (b.equals("0")) {
            return a;
        }

        StringBuffer res = new StringBuffer();
        int a_len = a.length();
        int b_len = b.length();
        int carry = 0;

        for (int i = 0; i < Math.max(a_len, b_len); i++) {
            int sum = carry;
            if (i < a.length()) {
                sum = sum + a.charAt(a_len - i - 1) - '0';
            }
            if (i < b.length()) {
                sum = sum + b.charAt(b_len - i - 1) - '0';
            }
            res.append(sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) {
            res.append(1);
        }

        return res.reverse().toString();
    }
}
