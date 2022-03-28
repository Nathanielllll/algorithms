package string;

/**
 * 翻转单词顺序
 * "I am a student."->".tneduts a ma I"->"student. a am I"
 * <p>
 * 只需要翻转两次即可
 */
public class Code_58 {
    /*
输入: " hello world! "
输出:"world! hello"
解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。

输入: "a good  example"
输出:"example good a"
解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */
    public String reverseWords(String s) {
        s = s.trim();
        int p1 = s.length() - 1;
        int p2 = p1;

        StringBuffer sb = new StringBuffer();
        while (p1 >= 0) {
            while (p1 >= 0 && s.charAt(p1) != ' ') {
                p1--;
            }
            sb.append(s.substring(p1 + 1, p2 + 1) + " ");
            while (p1 >= 0 && s.charAt(p1) == ' ') {
                p1--;
            }
            p2 = p1;
        }
        return sb.toString().trim();
    }
}
