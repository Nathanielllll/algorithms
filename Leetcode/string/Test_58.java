package string;

/**
 * @author luzhi
 * @date 2021/3/27
 */
public class Test_58 {
    /*
    给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。

    单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。

     

    示例 1：

    输入：s = "Hello World"
    输出：5
     */
    public static int lengthOfLastWord(String s) {
        int index = s.length() - 1;
        while (index >= 0 && s.charAt(index) == ' ') {
            index--;
        }
        int right = index;
        while (index >= 0 && s.charAt(index) != ' ') {
            index--;
        }
        int left = index;

        return right - left;
    }
}
