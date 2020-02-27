package dp;

/**
 * 最长不含重复字符的子字符串。如"arabcacfr"中位"acfr"
 * <p>
 * f(i)表示以第i个字符位结尾的不包含重复字符的子字符串的最长长度：
 * （1）如果第i个字符之前没有出现过，那么f(i)=f(i-1)+1;
 * （2）如果第i个字符之前已经出现过：
 *      （2.1）计算第i个字符和它上次出现在字符串中的位置的距离，记为d
 *          （2.1.1）d<=f(i-1)，则f(i)=d
 *          （2.1.2）d>f(i-1)，则仍然是f(i)=f(i-1)+1;
 */
public class Code_48 {
    public static int longestSubstringWithoutDuplication(String string) {
        if (string == null || string.length() <= 0) {
            return -1;
        }
        //最后输出值
        int max = 0;
        //用position来记录每个字母在string中出现的位置
        int[] positions = new int[26];
        for (int i = 0; i < 26; i++) {
            positions[i] = -1;
        }
        int length = 0;

        for (int i = 0; i < string.length(); i++) {
            //每个字符串在positions当中的位置index
            //如果'a'->0 'b'->1
            int index = string.charAt(i) - 'a';

            //这个字符串原来未出现过
            if (positions[index] == -1) {
                length++;
            //这个字符串原来出现过
            } else {
                int distance = i - positions[index];
                if (distance > length) {
                    length++;
                } else {
                    length = distance;
                }
            }
            //无论如何，都要在positions当中记录并改变位置
            positions[index] = i;
            if (max < length) {
                max = length;
            }
        }
        return max;
    }

    public static void main(String[] args) {
//        System.out.println('b'-'a');
        System.out.println(longestSubstringWithoutDuplication("arabcacfr"));//4
        System.out.println(longestSubstringWithoutDuplication("arabkacfr"));//6
        System.out.println(longestSubstringWithoutDuplication("a"));//1
        System.out.println(longestSubstringWithoutDuplication("abcdef"));//6
        System.out.println(longestSubstringWithoutDuplication("aaaaa"));//1
        System.out.println(longestSubstringWithoutDuplication("abcabcbb"));
    }
}
