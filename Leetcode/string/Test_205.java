package string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luzhi
 * @date 2021/3/27
 */
public class Test_205 {
    /*
    给定两个字符串s和t，判断它们是否是同构的。

    如果s中的字符可以按某种映射关系替换得到t，那么这两个字符串是同构的。
    
    每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。

    示例 1:
    
    输入：s = "egg", t = "add"
    输出：true
     */
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }
}
