package top100.Code_2;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class Coding_67 {
    public static String addBinary(String a, String b) {
        if (a.equals("0")) {
            return b;
        }
        if (b.equals("0")) {
            return a;
        }

        int carry = 0;
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
            int sum = carry;
            if (i < a.length()) {
                sum = sum + (a.charAt(a.length() - i - 1) - '0');
            }
            if (i < b.length()) {
                sum = sum + (b.charAt(b.length() - i - 1) - '0');
            }
            res.append(sum % 2);
            carry = sum / 2;
        }


//        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
//            int sum = carry;
//            sum = sum + (i >= 0 ? a.charAt(i) - '0' : 0);
//            sum = sum + (j >= 0 ? b.charAt(j) - '0' : 0);
//            res.append(sum % 2);
//            carry = sum / 2;
//        }

        if (carry == 1) {
            res.append(1);
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        System.out.println(addBinary(a, b));
    }
}
