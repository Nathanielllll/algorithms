package kuaishou.A_2020;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/*
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Test_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        int res = 0;
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;

        while (right < string.length()) {
            char right_char = string.charAt(right);
            window.put(right_char, window.getOrDefault(right_char, 0) + 1);
            right++;

            while (window.get(right_char) > 1) {
                char left_char = string.charAt(left);
                window.put(left_char, window.getOrDefault(left_char, 0) - 1);
                left++;
            }
            res = Math.max(res, right - left);

        }
        System.out.println(res);
    }
}
