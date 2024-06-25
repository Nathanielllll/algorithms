package array;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * <p>
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌上的数值。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3 输出：true 解释：Alice 手中的牌可以被重新排列为
 * [1,2,3]，[2,3,4]，[6,7,8]。 解释 示例 2：
 * <p>
 * 输入：hand = [1,2,3,4,5], groupSize = 4 输出：false 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
 */
public class Test_846 {

  public boolean isNStraightHand(int[] hand, int groupSize) {
    if (hand.length % groupSize != 0) {
      return false;
    }
    Map<Integer, Integer> cnt = new HashMap<>();
    for (int h : hand) {
      cnt.put(h, cnt.getOrDefault(h, 0) + 1);
    }
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(cnt.keySet());

    while (!minHeap.isEmpty()) {
      int first = minHeap.peek();
      for (int i = 0; i < groupSize; i++) {
        int num = i + first;
        if (!cnt.containsKey(num)) {
          return false;
        }
        cnt.put(num, cnt.get(num) - 1);
        if (cnt.get(num) == 0) {
          if (minHeap.isEmpty() || num != minHeap.peek()) {
            return false;
          }
          minHeap.poll();
        }
      }
    }
    return true;
  }

}
