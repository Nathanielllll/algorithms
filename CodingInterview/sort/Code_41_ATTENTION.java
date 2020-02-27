package sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 使用一个大顶堆和一个小顶堆来实现求大数情况下的中位数
 * <p>
 * 当两个堆中元素为偶数个，将新加入元素加入到小顶堆。如果要加入的数据比大顶堆的最大元素小，
 * 先将该元素插入大顶堆，然后将大顶堆的最大元素插入到小顶堆。
 * <p>
 * 当两个堆中元素为奇数个，将新加入元素加入到大顶堆。如果要加入的数据比小顶堆的最小元素大，
 * 先将该元素插入小顶堆，然后将小顶堆的最小元素插入到大顶堆。
 */
public class Code_41_ATTENTION {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public void insert(Integer number) {
        // 先加入maxHeap中，再从maxHeap中poll最大值放入minHeap。
        // 这样一定能保证小于中位数的在maxHeap，大于中位数的在minHead
        maxHeap.offer(number);
        minHeap.offer(maxHeap.poll());
        //如果不平衡则调整，保证maxHeap.size() > minHeap.size()
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) * 0.5;
        }
        return maxHeap.peek();
    }
}
