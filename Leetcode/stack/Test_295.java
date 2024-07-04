package stack;

import java.util.PriorityQueue;

public class Test_295 {

  /**
   * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
   * <p>
   * 例如 arr = [2,3,4] 的中位数是 3 。 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。 实现 MedianFinder 类:
   * <p>
   * MedianFinder() 初始化 MedianFinder 对象。
   * <p>
   * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
   * <p>
   * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
   * <p>
   * 示例 1：
   * <p>
   * 输入 ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"] [[], [1], [2],
   * [], [3], []] 输出 [null, null, null, 1.5, null, 2.0]
   * <p>
   * 解释 MedianFinder medianFinder = new MedianFinder(); medianFinder.addNum(1);    // arr = [1]
   * medianFinder.addNum(2);    // arr = [1, 2] medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
   * medianFinder.addNum(3);    // arr[1, 2, 3] medianFinder.findMedian(); // return 2.0
   */

  class MedianFinder {

    //small elements - maxHeap
    PriorityQueue<Integer> smallHeap;
    // large elements - minHeap
    PriorityQueue<Integer> largeHeap;

    public MedianFinder() {
      // 较小的数，大顶堆
      smallHeap = new PriorityQueue<>((a, b) -> b - a);
      largeHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
      smallHeap.offer(num);
      largeHeap.offer(smallHeap.poll());
      if (smallHeap.size() < largeHeap.size()) {
        smallHeap.offer(largeHeap.poll());
      }
    }

    public double findMedian() {
      if (smallHeap.isEmpty()) {
        return 0.0;
      } else {
        if (smallHeap.size() == largeHeap.size()) {
          return (smallHeap.peek() + largeHeap.peek()) * 0.5;
        } else {
          return smallHeap.peek() * 1.0;
        }
      }
    }
  }
}
