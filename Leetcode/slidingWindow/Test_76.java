package slidingWindow;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * <p>
 * <p>
 * 滑动窗口算法的思路是这样：
 * <p>
 * 1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，把索引闭区间 [left, right] 称为一个「窗口」。
 * <p>
 * 2、我们先不断地增加 right 指针扩大窗口 [left, right]，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
 * <p>
 * 3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right]，直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，每次增加 left，我们都要更新一轮结果。
 * <p>
 * 4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
 */
public class Test_76 {
    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> needs = new HashMap<>();
        //字符串target放入hashMap
        for (int i = 0; i < t.length(); i++) {
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }

        //result在s当中开始的位置和长度
        int minLength = Integer.MAX_VALUE ;
        int start = 0;

        //滑动窗口的双指针
        int left = 0;
        int right = 0;
        //匹配的个数
        int match = 0;

        while (right < s.length()) {
            // right对应的character
            Character character_right = s.charAt(right);
            if (needs.containsKey(character_right)) {
                //可能：window当中的character_left >= needs当中的character_left
                window.put(character_right, window.getOrDefault(character_right, 0) + 1);

                //character匹配的时候
                if (window.get(character_right).equals(needs.get(character_right))) {
                    match++;
                }
            }
            right++;

            //当right达到符合条件的最右边
            while (match == needs.size()) {
                //更新result
                if (right - left < minLength) {
                    minLength = right - left;
                    start = left;
                }

                Character character_left = s.charAt(left);
                //如果包含character_left，window减少
                if (needs.containsKey(character_left)) {
                    //window当中的character_left >= needs当中的character_left
                    window.put(character_left, window.get(character_left) - 1);
                    //不再相等的时候
                    if (window.get(character_left) < needs.get(character_left)) {
                        match--;
                    }
                }
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }

    public static void main(String[] args) {


        String S = "ADOBECODEBANC", T = "ABC";

        System.out.println(minWindow(S, T));
    }

}
