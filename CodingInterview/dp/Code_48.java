package dp;

import java.util.HashMap;

public class Code_48 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> window = new HashMap<>();
        int res = 0;
        int slow = 0;
        int fast = 0;
        while (fast < s.length()) {
            char c_fast = s.charAt(fast);
            window.put(c_fast, window.getOrDefault(c_fast, 0) + 1);
            fast++;

            // 让slow右移动，直到window里面c_fast的个数为1
            while (window.get(c_fast) > 1) {
                char c_slow = s.charAt(slow);
                window.put(c_slow, window.getOrDefault(c_slow, 0) - 1);
                slow++;
            }
            res = Math.max(res, fast - slow);
        }
        return res;
    }
}
