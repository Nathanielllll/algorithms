package stack.monotonousStack;

import java.util.HashMap;

public class Test_316 {
    public String removeDuplicateLetters(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for(char c : s.toCharArray()){
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }

        StringBuffer ans = new StringBuffer();
        for(char c : s.toCharArray()){
            if (hashMap.get(c) > 1) {
                hashMap.put(c, hashMap.get(c) - 1);
            }else {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
