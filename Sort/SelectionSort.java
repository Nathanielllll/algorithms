public class SelectionSort {

  public static int[] selectionSort(int[] nums) {
    if (nums.length == 0) {
      return nums;
    }

    for (int i = 0; i < nums.length - 1; i++) {
      int minIdx = i;
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[minIdx] > nums[j]) {
          minIdx = j;
        }
      }
      if (i != minIdx) {
        swap(nums, i, minIdx);
      }
    }
    return nums;
  }

  public static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 7, 3, 2, 5, 9, 10};
    selectionSort(nums);
  }
}
