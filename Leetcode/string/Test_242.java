package string;

/**
 * @author luzhi
 * @date 2021/3/29
 */
public class Test_242 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] store = new int[26];
        for (int i = 0; i < s.length(); i++) {
            store[s.charAt(i) - 'a']++;
            store[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (store[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
