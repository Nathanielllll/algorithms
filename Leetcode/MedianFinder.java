import java.util.PriorityQueue;

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