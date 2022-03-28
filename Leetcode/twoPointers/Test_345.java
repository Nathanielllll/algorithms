package twoPointers;

import java.util.HashSet;
/*
编写一个函数，以字符串作为输入，反转该字符串中的元音字母。



示例 1：

输入："hello"
输出："holle"
示例 2：

输入："leetcode"
输出："leotcede"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Test_345 {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String v = "aeiouAEIOU";
        HashSet<Character> hashSet = new HashSet<>();
        for (int i = 0; i < v.length(); i++) {
            hashSet.add(v.charAt(i));
        }

        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char left = chars[i];
            char right = chars[j];
            if (hashSet.contains(left) && hashSet.contains(right)) {
                swap(chars, i, j);
                i++;
                j--;
            } else {
                if (!hashSet.contains(left)) {
                    i++;
                }
                if (!hashSet.contains(right)) {
                    j--;
                }
            }
        }
        return String.valueOf(chars);
    }

    public void swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
