package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 有 n 个网络节点，标记为 1 到 n。
 * <p>
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi
 * 是一个信号从源节点传递到目标节点的时间。
 * <p>
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * <p>
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2 输出：2
 */
public class Test_743 {

  // Dijkstra，无限个节点
  public int networkDelayTime(int[][] times, int n, int k) {
    Map<Integer, List<int[]>> edges = new HashMap<>();
    for (int[] time : times) {
      edges.computeIfAbsent(time[0], key -> new ArrayList<>()).add(new int[]{time[1], time[2]});
    }

    // int[0]是距离root的最短距离，int[1]是节点号
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
    minHeap.offer(new int[]{0, k});
    // 访问过的节点号
    Set<Integer> visited = new HashSet<>();

    int result = 0;
    while (!minHeap.isEmpty()) {
      int[] curr = minHeap.poll();
      int minDis = curr[0];
      int idx = curr[1];
      if (visited.contains(idx)) {
        continue;
      }
      visited.add(idx);
      // 会被一直更新，直到最长的距离
      result = minDis;

      if (edges.get(idx) != null) {
        for (int[] edge : edges.get(idx)) {
          int curIdx = edge[0];
          int dis = edge[1];
          if (!visited.contains(curIdx)) {
            minHeap.add(new int[]{dis + minDis, curIdx});
          }
        }
      }
    }
    return visited.size() == n ? result : -1;
  }
}
