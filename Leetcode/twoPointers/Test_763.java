package twoPointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author luzhi
 * @date 2021/4/16
 */
public class Test_763 {
    /*
        字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。

        示例：

        输入：S = "ababcbacadefegdehijhklij"
        输出：[9,7,8]
        解释：
        划分结果为 "ababcbaca", "defegde", "hijhklij"。
        每个字母最多出现在一个片段中。
        像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
         */
    public static List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            lastIndex.put(S.charAt(i), i);
        }

        int length = S.length();

        int left = 0;
        int right = 0;
        while (right < length) {
            int l = left;
            int r = right;
            while (l <= r) {
                if (l < length && lastIndex.get(S.charAt(l)) <= r) {
                    l++;
                } else {
                    r = lastIndex.get(S.charAt(l));
                }
            }
            result.add(r - left + 1);

            right = r + 1;
            left = right;
        }

        return result;
    }
}
