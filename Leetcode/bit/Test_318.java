package bit;

/**
 * 给定一个字符串数组words，找到length(word[i]) * length(word[j])的最大值，并且这两个单词不含有公共字母。
 * 你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 *
 */
public class Test_318 {
    public static int maxProduct(String[] words) {
        // 构造出每个word各个字母出现的情况。出现则bit为1，否则为0
        int[] bits = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int bit = 0;
            for (int j = 0; j < words[i].length(); j++) {
                bit = bit | (1 << (words[i].charAt(j) - 'a'));
            }
            bits[i] = bit;
        }

        int result = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < i; j++) {
                if ((bits[i] & bits[j]) == 0) {
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }
}
