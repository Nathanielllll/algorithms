import java.util.Arrays;

public class QuickSort {
    public void quickSort(int[] nums, int start, int end){
        if (start < end) {
            int index = partition(nums, start, end);
            quickSort(nums, start, index - 1);
            quickSort(nums, index + 1, end);
        }
    }

    public int partition(int[] nums, int left, int right){
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
        swap(nums, left, right);
        return left;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3,9,5,6,11,7,8};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
