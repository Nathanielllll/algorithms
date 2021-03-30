package string;

import java.util.HashMap;

/**
 * @author luzhi
 * @date 2021/3/29
 */
public class Test_242 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            count.put(ch, count.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            count.put(ch, count.getOrDefault(ch, 0) - 1);
        }

        for (Character ch : count.keySet()) {
            if (count.get(ch) != 0) {
                return false;
            }
        }
        return true;
    }
}
