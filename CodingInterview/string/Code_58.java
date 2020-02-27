package string;

/**
 * 翻转单词顺序
 * "I am a student."->".tneduts a ma I"->"student. a am I"
 * <p>
 * 只需要翻转两次即可
 */
public class Code_58 {
    public static void reverse(StringBuffer string, int start, int end) {
        char temp;
        while (start <= end) {
            temp = string.charAt(end);
            string.setCharAt(end, string.charAt(start));
            string.setCharAt(start, temp);
            start++;
            end--;
        }
    }

    public static void reverseSentence(StringBuffer sentence) {
        if (sentence == null || sentence.length() <= 0) {
            return;
        }

        reverse(sentence, 0, sentence.length() - 1);
        int start = 0;
        int end = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c != ' ') {
                end++;
            }
            if (c == ' ' || end == sentence.length()) {
                reverse(sentence, start, end - 1);
                end++;
                start = end;
            }
        }
    }

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("He is a  student.");
        reverseSentence(stringBuffer);
        System.out.println(stringBuffer);
    }
}
