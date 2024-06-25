import java.util.PriorityQueue;

class MedianFinder {

  // large elements - minHeap
  private final PriorityQueue<Integer> largeHeap;
  //small elements - maxHeap
  private final PriorityQueue<Integer> smallHeap;

  public MedianFinder() {
    largeHeap = new PriorityQueue<>();
    smallHeap = new PriorityQueue<>((a, b) -> b - a);
  }

  public void addNum(int num) {
    // 方案一：
//      largeHeap.offer(num);
//      smallHeap.offer(largeHeap.poll());
//      if (largeHeap.size() < smallHeap.size()) {
//        largeHeap.offer(smallHeap.poll());
//      }

    // 方案二：
    largeHeap.offer(num);
    if ((largeHeap.size() - smallHeap.size() > 1)
        ||
        (!largeHeap.isEmpty() && !smallHeap.isEmpty() && largeHeap.peek() < smallHeap.peek())) {
      smallHeap.offer(largeHeap.poll());
    }
    if (smallHeap.size() > largeHeap.size()) {
      largeHeap.offer(smallHeap.poll());
    }
  }

  public double findMedian() {
    if (largeHeap.isEmpty()) {
      return 0.0;
    } else {
      if (largeHeap.size() == smallHeap.size()) {
        return (largeHeap.peek() + smallHeap.peek()) * 0.5;
      } else {
        return largeHeap.peek() * 1.0;
      }
    }
  }
}