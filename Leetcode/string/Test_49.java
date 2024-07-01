package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"], 输出: [ ["ate","eat","tea"], ["nat","tan"], ["bat"]
 * ]
 */
public class Test_49 {

  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      int[] store = new int[26];
      for (int i = 0; i < str.length(); i++) {
        store[str.charAt(i) - 'a']++;
      }
      String storeStr = Arrays.toString(store);

      List<String> list = map.getOrDefault(storeStr, new ArrayList<>());
      list.add(str);
      map.put(storeStr, list);
    }
    return new ArrayList<>(map.values());
  }

}
