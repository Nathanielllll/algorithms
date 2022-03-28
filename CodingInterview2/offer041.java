import java.util.LinkedList;
import java.util.Queue;

public class offer041 {
    static class MovingAverage {

        /**
         * Initialize your data structure here.
         */
        Queue<Integer> queue;
        int size;
        int sum;

        public MovingAverage(int size) {
            this.queue = new LinkedList<>();
            this.size = size;
            sum = 0;
        }

        public double next(int val) {
            if (!queue.isEmpty() && queue.size() >= this.size) {
                sum -= queue.poll();
            }
            queue.add(val);
            sum += val;
            return ((double) sum) / queue.size();
        }
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1));
        System.out.println(movingAverage.next(10));
        System.out.println(movingAverage.next(3));
        System.out.println(movingAverage.next(5));
    }
}
