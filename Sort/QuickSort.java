import java.util.Arrays;

public class QuickSort {
    // 选择最右边的作为目标值，在nums为升序的情况下，复杂度为O(N^2)，
    // 因为一轮partition的时间复杂度为O(N)

    // 如果划分的位置，导致左右两边都相等，那么时间复杂度为O(NlogN)
    public void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            // 加上这个，说明是随机快排。start~end中位置随机选一个，然后和end交换
            swap(nums, start + (int) (Math.random() * (end - start + 1)), end);

            int[] indexes = partition(nums, start, end);
            quickSort(nums, start, indexes[0] - 1);
            quickSort(nums, indexes[1] + 1, end);
        }
    }

    // less 表示<= nums[right]的区域，
    // more 表示 > nums[right]的区域
    public int[] partition(int[] nums, int left, int right) {
        int less = left - 1;
        int more = right;

        int target = nums[right];
        while (left != more) {
            if (nums[left] < target) {
                swap(nums, ++less, left++);
            } else if (nums[left] > target) {
                swap(nums, --more, left);
            } else {
                left++;
            }
        }
        // 把第一个> nums[right]的位置（more）和right位置交换
        swap(nums, more, right);
        // 此时 less+1是第一个== nums[right]的位置；more是最后一个 == nums[right]的位置
        return new int[]{less + 1, more};
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 9, 5, 6, 11, 7, 8};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
