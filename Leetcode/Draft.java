import java.util.*;

public class Draft {
    public void heap_sort(int[] nums){
        int n = nums.length;
        build_heap(nums, n);
        for (int i = n-1; i >= 0; i--) {
            swap(nums, i, 0);
            heapify(nums, i, 0);
        }
    }



    public void build_heap(int[] nums, int n) {
        int last_index = n - 1;
        int last_parent_index = (last_index - 1) / 2;
        for (int i = last_parent_index; i >= 0; i--) {
            heapify(nums, n, i);
        }
    }

    public void heapify(int[] nums, int n, int i) {
        if (i > n) {
            return;
        }

        int left_index = i * 2 + 1;
        int right_index = i * 2 + 2;

        int max_index = i;
        if (nums[left_index] > nums[max_index]) {
            max_index = left_index;
        }
        if (nums[right_index] > nums[max_index]) {
            max_index = right_index;
        }
        if (max_index != i) {
            swap(nums, max_index, i);
            heapify(nums, n, max_index);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}