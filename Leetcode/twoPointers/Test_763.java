package twoPointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author luzhi
 * @date 2021/4/16
 */
public class Test_763 {

  /*
      字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。

      示例：

      输入：S = "ababcbacadefegdehijhklij"
      输出：[9,7,8]
      解释：
      划分结果为 "ababcbaca", "defegde", "hijhklij"。
      每个字母最多出现在一个片段中。
      像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
       */
  public List<Integer> partitionLabels(String s) {
    Map<Character, Integer> charToLastIdx = new HashMap<>();
    char[] chars = s.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      charToLastIdx.put(chars[i], i);
    }

    int curLen = 0;
    int goal = 0;
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < chars.length; i++) {
      curLen++;
      char ch = chars[i];
      // 到目前为止，最大的目标。
      goal = Math.max(goal, charToLastIdx.get(ch));
      if (goal == i) {
        result.add(curLen);
        curLen = 0;
      }
    }
    return result;
  }
}
