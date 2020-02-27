package string;

import java.util.HashMap;

/**
 * 字符串S和 T 只包含小写字符。在S中，所有字符只会出现一次。
 *
 * S 已经根据某种规则进行了排序。我们要根据S中的字符顺序对T进行排序。更具体地说，如果S中x在y之前出现，那么返回的字符串中x也应出现在y之前。
 *
 * 返回任意一种符合条件的字符串T。
 *
 * 示例:
 * 输入:
 * S = "cba"
 * T = "abcd"
 * 输出: "cbad"
 * 解释:
 * S中出现了字符 "a", "b", "c", 所以 "a", "b", "c" 的顺序应该是 "c", "b", "a".
 * 由于 "d" 没有在S中出现, 它可以放在T的任意位置. "dcba", "cdba", "cbda" 都是合法的输出。
 */
public class Test_791 {
    public String customSortString(String S, String T) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        // 将T的char全部放入hashMap当中
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }

        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (hashMap.containsKey(c)) {
                for (int j = 0; j < hashMap.get(c); j++) {
                    ans.append(c);
                }
                hashMap.put(c, 0);
            }
        }

        for (char c : hashMap.keySet()) {
            for (int i = 0; i < hashMap.get(c); i++) {
                ans.append(c);
            }
            hashMap.put(c, 0);
        }

        return ans.toString();
    }
}
