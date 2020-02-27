package hash;

import java.util.HashMap;

/**
 * 第一个只出现一次的字符。如"abaccdeff"则输出'b'
 *
 * 使用hashmap遍历两次字符串即可
 */
public class Code_50 {
    public static void firstNotRepeatingChar(String string) {
        if (string == null || string.length() <= 0) {
            throw new RuntimeException("invalid input");
        }

        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < string.length(); i++) {
            if (hashMap.containsKey(string.charAt(i))) {
                int value = hashMap.get(string.charAt(i));
                hashMap.put(string.charAt(i), value + 1);
            } else {
                hashMap.put(string.charAt(i), 1);
            }
        }

        for (int i = 0; i < string.length(); i++) {
            if (hashMap.get(string.charAt(i)) == 1){
                System.out.println(string.charAt(i));
                break;
            }
        }
    }

    public static void main(String[] args) {
        firstNotRepeatingChar("abaccdeff");
    }
}
