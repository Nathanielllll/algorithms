import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    /** initialize your data structure here. */
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;
    //假设maxHeap的个数要比minHeap的个数多
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    
    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.isEmpty()) {
            return 0.0;
        }
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) * 0.5;
        }else {
            return maxHeap.peek() * 1.0;
        }
    }
}
