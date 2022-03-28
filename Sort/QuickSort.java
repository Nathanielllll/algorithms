import java.util.Random;

public class QuickSort {
    public void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int idx = partition(nums, start, end);
            quickSort(nums, start, idx - 1);
            quickSort(nums, idx + 1, end);
        }
    }

    private Random random = new Random(System.currentTimeMillis());

    public int partition(int[] nums, int left, int right) {
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

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] nums = {4,5,7,3,2,3,5,1,1,2,3};
        quickSort.quickSort(nums, 0, nums.length - 1);
    }
}
