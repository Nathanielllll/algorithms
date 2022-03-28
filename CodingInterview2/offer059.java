import java.util.PriorityQueue;

public class offer059 {
    class KthLargest {
        PriorityQueue<Integer> minHeap;
        int k;

        public KthLargest(int k, int[] nums) {
            minHeap = new PriorityQueue<>();
            this.k = k;

            for (int num : nums) {
                minHeap.add(num);
                if (minHeap.size() > this.k) {
                    minHeap.poll();
                }
            }
        }

        public int add(int val) {
            minHeap.add(val);
            if (minHeap.size() > this.k) {
                minHeap.poll();
            }
            return minHeap.peek();
        }
    }
}
