public class SelectionSort {
    public static int[] selectionSort(int[] nums) {
        if (nums.length == 0)
            return nums;

        for (int i = nums.length - 1; i >= 0; i--) {
            int max_index = i;
            for (int j = 0; j < i; j++) {
                if (nums[max_index] < nums[j]) {
                    max_index = j;
                }
            }
            swap(nums, i, max_index);
        }
        return nums;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,7,3,2,5,9,10};
        selectionSort(nums);
    }
}
