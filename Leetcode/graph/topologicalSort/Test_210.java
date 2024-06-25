package graph.topologicalSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Test_210 {

  public static int[] findOrder(int numCourses, int[][] prerequisites) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();
    int[] in = new int[numCourses];
    for (int[] edge : prerequisites) {
      int pre = edge[1];
      int next = edge[0];
      in[next]++;
      graph.computeIfAbsent(pre, k -> new HashSet<>()).add(next);
    }

    List<Integer> resultList = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < in.length; i++) {
      if (in[i] == 0) {
        queue.add(i);
      }
    }
    while (!queue.isEmpty()) {
      int cnt = queue.size();
      for (int i = 0; i < cnt; i++) {
        int course = queue.poll();
        resultList.add(course);
        Set<Integer> nexts = graph.get(course);
        if (nexts != null) {
          for (Integer next : nexts) {
            in[next]--;
            if (in[next] == 0) {
              queue.add(next);
            }
          }
        }
      }
    }
    for (int i = 0; i < numCourses; i++) {
      if (in[i] != 0) {
        return new int[]{};
      }
    }

    int[] result = new int[resultList.size()];
    for (int i = 0; i < resultList.size(); i++) {
      result[i] = resultList.get(i);
    }
    return result;
  }
}
