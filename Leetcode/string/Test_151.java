package string;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出:"blue is sky the"
 * 示例 2：
 *
 * 输入: " hello world! "
 * 输出:"world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good  example"
 * 输出:"example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */
public class Test_151 {
    public static String reverseWords(String s) {
        // 如 ""
        if(s==null || s.length()<=0){
            return "";
        }
        // 如 "a"
        if(s.length()==1 && s.charAt(0)!=' '){
            return s;
        }

        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while(left <= right && chars[left] == ' '){
            left++;
        }
        // 这种情况 "    "
        if(left==chars.length){
            return "";
        }
        while(left <= right && chars[right] == ' '){
            right--;
        }
        // 这种情况 "  a  "
        if(left == right){
            return String.valueOf(chars[left]);
        }

        char[] chars1 = new char[right - left + 1];
        for (int i = 0; i < chars1.length; i++) {
            chars1[i] = chars[i + left];
        }

        reverse(chars1, 0, chars1.length - 1);

        int start = 0;
        int end = 0;

        while (end < chars1.length) {
            char c = chars1[end];
            if (c != ' ') {
                end++;
            }
            if (c == ' ' || end == chars1.length) {
                reverse(chars1, start, end - 1);
                end++;
                start = end;
            }
        }

//        return String.valueOf(chars1);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < chars1.length; i++) {
            if (i > 0 && chars1[i - 1] == ' ' && chars1[i] == ' ') {
                continue;
            }else {
                stringBuffer.append(chars1[i]);
            }
        }

        return stringBuffer.toString();
    }

    private static void reverse(char[] chars, int start, int end) {
        char temp;
        while (start <= end) {
            temp = chars[end];
            chars[end] = chars[start];
            chars[start] = temp;
            start++;
            end--;
        }
    }
    public static void main(String[] args) {
        String s = "a good   example  ";
        System.out.println(reverseWords(s));
    }
}
