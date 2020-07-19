package array;

public class Test_215 {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
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

    public static int partition(int[] nums, int left, int right){
        int target = nums[right];
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (nums[left] < target) {
                swap(nums, ++less, left++);
            } else if (nums[left] > target) {
                swap(nums, --more, left);
            }else {
                left++;
            }
        }
        swap(nums, left, right);
        return left;
    }

    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
