public class HeapSort {
    // 大顶堆用来升序排序
    public void heapSort(int[] nums) {
        build_heap(nums, nums.length);

        for (int i = nums.length - 1; i >= 0; i--) {
            // 第0号索引和第i号索引互换
            swap(nums, 0, i);
            // 此时的长度为i，是前一次的长度-1
            heapify(nums, i, 0);
        }
    }


    // 大顶堆
    public void build_heap(int[] nums, int length) {
        int last_index = length - 1;
        int last_parent_index = (last_index - 1) / 2;
        for (int i = last_parent_index; i >= 0; i--) {
            heapify(nums, length, i);
        }
    }


    private void heapify(int[] nums, int length, int index) {
        if (index > length) {
            return;
        }
        int left_index = index * 2 + 1;
        int right_index = index * 2 + 2;
        int max_index = index;
        if (left_index < length && nums[left_index] > nums[max_index]) {
            max_index = left_index;
        }

        if (right_index < length && nums[right_index] > nums[max_index]) {
            max_index = right_index;
        }

        if (index != max_index) {
            swap(nums, index, max_index);
            heapify(nums, length, max_index);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
