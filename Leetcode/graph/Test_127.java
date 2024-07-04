package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 * <p>
 * 每一对相邻的单词只差一个字母。 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。 sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目
 * 。如果不存在这样的转换序列，返回 0 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"] 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。 示例 2：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"] 输出：0 解释：endWord
 * "cog" 不在字典中，所以无法进行转换。
 */
public class Test_127 {

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return 0;
    }
    if (!wordList.contains(beginWord)) {
      wordList = new ArrayList<>(wordList);
      wordList.add(beginWord);
    }
    Map<String, List<String>> graph = new HashMap<>();
    for (int i = 0; i < wordList.size(); i++) {
      for (int j = i + 1; j < wordList.size(); j++) {
        String w1 = wordList.get(i);
        String w2 = wordList.get(j);
        if (isClose(w1, w2)) {
          graph.putIfAbsent(w1, new ArrayList<>());
          graph.putIfAbsent(w2, new ArrayList<>());
          graph.get(w1).add(w2);
          graph.get(w2).add(w1);
        }
      }
    }

    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    queue.add(beginWord);
    visited.add(beginWord);
    int level = 1;
    while (!queue.isEmpty()) {
      int cnt = queue.size();
      for (int i = 0; i < cnt; i++) {
        String curWord = queue.poll();
        if (curWord.equals(endWord)) {
          return level;
        }
        for (String neighbor : graph.getOrDefault(curWord, new ArrayList<>())) {
          if (!visited.contains(neighbor)) {
            queue.add(neighbor);
            visited.add(neighbor);
          }
        }
      }
      level++;
    }
    return 0;
  }

  private boolean isClose(String w1, String w2) {
    int n = w1.length();
    int diffCnt = 0;
    for (int i = 0; i < n; i++) {
      if (w1.charAt(i) == w2.charAt(i)) {
        continue;
      }
      diffCnt++;
      // 提前退出
      if (diffCnt > 1) {
        return false;
      }
    }
    return diffCnt == 1;
  }
}
