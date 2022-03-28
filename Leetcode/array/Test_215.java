package array;

import java.util.PriorityQueue;
import java.util.Random;

public class Test_215 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));
    }

    public static int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;

        int index = partition(nums, start, end);
        int target_index = nums.length - k;
        while (index != target_index) {
            if (index > target_index) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = partition(nums, start, end);
        }

        return nums[target_index];
    }
    private static Random random = new Random(System.currentTimeMillis());
    public static int partition(int[] nums, int left, int right) {
        if (right > left) {
            int randomIdx = left + 1 + random.nextInt(right - left);
            swap(nums, left, randomIdx);
        }

        int j = left;
        int pivot = nums[left];
        // all in [left + 1, lt] < pivot
        // all in [lt + 1, i) >= pivot
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                ++j;
                swap(nums, i, j);
            }
        }
        swap(nums, j, left);
        // 交换以后 nums[left..j - 1] < pivot, nums[j] = pivot, nums[j + 1..right] >= pivot
        return j;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 方法二：小根堆
    public static int findKthLargest_1(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}
