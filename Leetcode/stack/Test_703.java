package stack;

import java.util.PriorityQueue;

public class Test_703 {
    class KthLargest {

        PriorityQueue<Integer> minHeap;
        int k;
        public KthLargest(int k, int[] nums) {
            this.k = k;
            minHeap = new PriorityQueue<>();
            for(int num : nums){
                minHeap.add(num);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        public int add(int val) {
            minHeap.add(val);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            return minHeap.peek();
        }
    }
}
