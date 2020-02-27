package string;

/**
 * 左旋转字符串
 * 如"abcdefg"和数字2->"gfedcba"->"cdefgab"
 * <p>
 * 实际上翻转三次字符串
 */
public class Code_58_2 {
    public static void reverse(StringBuffer string, int start, int end) {
        char temp;
        while (start < end) {
            temp = string.charAt(end);
            string.setCharAt(end, string.charAt(start));
            string.setCharAt(start, temp);
            start++;
            end--;
        }
    }

    public static void leftRotateString(StringBuffer string, int n) {
        if (string == null || string.length() <= 0 || n < 0 || n >= string.length()) {
            return;
        }
        int length = string.length() - 1;
        reverse(string, 0, length);
        reverse(string, 0, length - n);
        reverse(string, length - n + 1, length);
    }

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("abcdefg");
        leftRotateString(stringBuffer, 2);
        System.out.println(stringBuffer);
    }
}
