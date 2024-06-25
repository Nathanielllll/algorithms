public class BubbleSort {

  public static int[] bubbleSort(int[] nums) {
    if (nums.length == 0) {
      return nums;
    }

    boolean flag = true;
    for (int i = nums.length - 1; i >= 0; i--) {
      for (int j = 0; j < i; j++) {
        if (nums[j] > nums[j + 1]) {
          swap(nums, j, j + 1);
          flag = false;
        }
      }
      if (flag) {
        break;
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
    bubbleSort(nums);
  }
}
