import java.util.Arrays;

public class HeapSort {
    public void heapSort(int[] nums) {
        build_heap(nums, nums.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, i, 0);
            heapify(nums, i, 0);
        }
    }

    /**
     *
     * @param nums
     * @param n 数组的长度
     */
    public void build_heap(int[] nums, int n) {
        int last = n - 1;
        int last_parent = (last - 1) / 2;
        for (int i = last_parent; i >= 0; i--) {
            heapify(nums, n, i);
        }
    }

    /**
     *
     * @param nums
     * @param n 数组的长度
     * @param i 数组的某个node
     */
    public void heapify(int[] nums, int n, int i) {
        //递归出口
        if (i > n) {
            return;
        }

        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int max = i;
        if (left < n && nums[left] > nums[max]) {
            max = left;
        }
        if (right < n && nums[right] > nums[max]) {
            max = right;
        }
        if (max != i) {
            swap(nums, max, i);
            heapify(nums, n, max);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {6,5,4,3,2,1};
        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
